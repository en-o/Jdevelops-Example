package cn.tannn.jdevelopssbootjpademo;

import cn.hutool.core.lang.func.Supplier3;
import cn.hutool.core.util.ReflectUtil;
import cn.jdevelops.data.jap.annotation.JpaSelectIgnoreField;
import cn.jdevelops.data.jap.annotation.JpaSelectWrapperOperator;
import cn.jdevelops.data.jap.core.Specifications;
import cn.jdevelops.data.jap.core.specification.OperatorWrapper;
import cn.jdevelops.data.jap.core.specification.SpecificationWrapper;
import cn.jdevelops.data.jap.enums.SQLConnect;
import cn.jdevelops.data.jap.enums.SQLOperatorWrapper;
import cn.jdevelops.data.jap.util.IObjects;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JpaSpecificationsTest {


    /**
     * 根据实体自动组装
     *
     * @param bean 构造的查询对象
     * @param <R>  返回对象
     * @param <B>  查询对象
     * @return Specification
     */
    public static <R, B> Specification<R> beanWhere(B bean) {
        return beanWhere(bean, e -> {
        });
    }
    /**
     * 根据实体自动组装 + 自定义查询
     *
     * @param action 操作
     * @param bean   构造的查询对象
     * @param <R>    返回对象
     * @param <B>    查询对象
     * @return Specification
     */
    public static <R, B> Specification<R> beanWhere(B bean, Consumer<SpecificationWrapper<R>> action) {
        Field[] fields = ReflectUtil.getFields(bean.getClass());
        return Arrays.stream(fields)
                .filter(field -> {
                    JpaSelectWrapperOperator wrapperOperator = field.getAnnotation(JpaSelectWrapperOperator.class);
                    JpaSelectIgnoreField ignoreField = field.getAnnotation(JpaSelectIgnoreField.class);
                    // 字段值
                    Object fieldValue = ReflectUtil.getFieldValue(bean, field);
                    // 字段名
                    String fieldName = field.getName();
                    if ("serialVersionUID".equals(fieldName)) {// 忽略一些固定字段
                        return false;
                    } else if (IObjects.nonNull(ignoreField)) { // 过滤被忽略的
                        return false;
                    } else if (IObjects.nonNull(wrapperOperator)) { // 过滤值可以为空的
                        // true表示会判断value是否为空，空则不做查询条件，不空则做查询条件
                        // 值为空就不要了
                        return !wrapperOperator.ignoreNull() || !IObjects.isNull(fieldValue);
                    } else if (IObjects.isNull(ignoreField) && IObjects.isNull(wrapperOperator) && IObjects.isNull(fieldValue)) {
                        // 如果没有忽略且没有加JpaSelectWrapperOperator注解的，会默认判空，空则不查了
                        return false;
                    }
                    return true;

                })
                .map(field -> {
                    // 构造查询
                    JpaSelectWrapperOperator wrapperOperator = field.getAnnotation(JpaSelectWrapperOperator.class);
                    // 字段值
                    Object fieldValue = ReflectUtil.getFieldValue(bean, field);
                    // 字段名
                    String fieldName = field.getName();
                    // 字段名
                    Specification<R> where = Specifications.<R>where(e -> {
                        // 默认 eq，且空值也查询
                        SQLOperatorWrapper operator = IObjects.nonNull(wrapperOperator) ? wrapperOperator.operatorWrapper() : SQLOperatorWrapper.EQ;
                        // 如果 值等于 list 则 使用 In 操作
                        if (fieldValue instanceof Collection) {
                            operator = SQLOperatorWrapper.IN;
                        }
                        // 构造 OperatorWrapper
                        OperatorWrapper wrapper = new OperatorWrapper(e, wrapperOperator, fieldName, fieldValue);
                        operator.consumer().accept(wrapper);
                        action.accept(e);
                    });
                    return where;
                }).reduce(Specification::and).orElse((root, criteriaQuery, criteriaBuilder) -> criteriaQuery.getRestriction());
    }

}
