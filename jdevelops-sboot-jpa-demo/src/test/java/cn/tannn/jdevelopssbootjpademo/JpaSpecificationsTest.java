package cn.tannn.jdevelopssbootjpademo;

import cn.hutool.core.util.ReflectUtil;
import cn.jdevelops.data.jap.annotation.JpaSelectIgnoreField;
import cn.jdevelops.data.jap.annotation.JpaSelectOperator;
import cn.jdevelops.data.jap.core.Specifications;
import cn.jdevelops.data.jap.core.specification.OperatorWrapper;
import cn.jdevelops.data.jap.core.specification.SpecificationWrapper;
import cn.jdevelops.data.jap.enums.SQLConnect;
import cn.jdevelops.data.jap.enums.SQLOperatorWrapper;
import cn.jdevelops.data.jap.enums.SpecBuilderDateFun;
import cn.jdevelops.data.jap.util.IObjects;
import cn.jdevelops.data.jap.util.JpaUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static cn.jdevelops.data.jap.core.Specifications.where;
import static org.apache.commons.lang3.BooleanUtils.or;

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
     * @param bean   构造的查询对象
     * @param action 除了bean还能自定义操作
     * @param <R>    返回对象
     * @param <B>    查询对象
     * @return Specification
     */
//    public static <R, B> Specification<R> beanWhere3(B bean, Consumer<SpecificationWrapper<R>> action) {
//        Field[] fields = ReflectUtil.getFields(bean.getClass());
//
//        Specification<R> where = where(true, action);
//        for (int i = 0, fieldsLength = fields.length; i < fieldsLength; i++) {
//            Field field = fields[i];
//            // 字段名
//            String fieldName = field.getName();
//            if ("serialVersionUID".equals(fieldName)) {
//                continue;
//            }
//            // 字段值
//            Object fieldValue = ReflectUtil.getFieldValue(bean, field);
//            // 字段被忽略
//            JpaSelectIgnoreField ignoreField = field.getAnnotation(JpaSelectIgnoreField.class);
//            if (IObjects.nonNull(ignoreField)) {
//                continue;
//            }
//            // 获取组装条件
//            JpaSelectOperator wrapperOperator = field.getAnnotation(JpaSelectOperator.class);
//
//            if (IObjects.nonNull(wrapperOperator)) {
//                // 需要判空，然后空值就不查了
//                if (wrapperOperator.ignoreNull() && IObjects.isNull(fieldValue)) {
//                    continue;
//                }
////                    wrapperOperator.connect().equals(SQLConnect.AND)
//                // 默认 eq，且空值也查询
//                SQLOperatorWrapper operator;
//                // 如果 值等于 list 则 使用 In 操作
//                if (fieldValue instanceof Collection) {
//                    operator = SQLOperatorWrapper.IN;
//                } else {
//                    operator = IObjects.nonNull(wrapperOperator) ? wrapperOperator.operatorWrapper() : SQLOperatorWrapper.EQ;
//                }
//                // 使用自定义的名字
//                if (!IObjects.isBlank(wrapperOperator.fieldName())) {
//                    fieldName = wrapperOperator.fieldName();
//                }
//                String finalFieldName = fieldName;
//                Specification<R> or1 = where.or(where(wrapperOperator.connect().equals(SQLConnect.AND), or -> {
//                    OperatorWrapper operatorWrapper = new OperatorWrapper(or, str2Path(or.getRoot()
//                            , or.getBuilder()
//                            , wrapperOperator.function()
//                            , finalFieldName
//                    ), fieldValue);
//                    operator.consumer().accept(operatorWrapper);
//                }));
//                action.accept(where(wrapperOperator.connect().equals(SQLConnect.AND), or -> {
//                    OperatorWrapper operatorWrapper = new OperatorWrapper(or, str2Path(or.getRoot()
//                            , or.getBuilder()
//                            , wrapperOperator.function()
//                            , finalFieldName
//                    ), fieldValue);
//                    operator.consumer().accept(operatorWrapper);
//                }));
//            }
//        }
//
//        return where;
//    }


    /**
     * 根据实体自动组装 + 自定义查询
     *
     * @param bean   构造的查询对象
     * @param action 除了bean还能自定义操作
     * @param <R>    返回对象
     * @param <B>    查询对象
     * @return Specification
     */
    public static <R, B> Specification<R> beanWhere(B bean, Consumer<SpecificationWrapper<R>> action) {
        Field[] fields = ReflectUtil.getFields(bean.getClass());
        Map<String, List<Field>> maps = Arrays.stream(fields).filter(field -> {
            JpaSelectOperator wrapperOperator = field.getAnnotation(JpaSelectOperator.class);
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

        }).collect(Collectors.groupingBy(field -> {
            // 获取组装条件
            JpaSelectOperator wrapperOperator = field.getAnnotation(JpaSelectOperator.class);
            if (IObjects.nonNull(wrapperOperator) && wrapperOperator.connect().equals(SQLConnect.AND)) {
                return "and";
            } else {
                return "or";
            }
        }));

        Specification<R> extend = where(true, action == null ? (e -> {
        }) : action);
        Specification<R> andSpec = where(true, and -> {
            maps.get("and").forEach(field -> {
                xxxx(field, and, bean);
            });
        });
        Specification<R> orSpec = where(false, or -> {
            maps.get("or").forEach(field -> {
                xxxx(field, or, bean);
            });
        });
        return andSpec.or(orSpec).and(extend);
    }


    public static <R, B> void xxxx(Field field, SpecificationWrapper<R> e, B bean) {
        // 字段名
        String fieldName = field.getName();
        // 字段值
        Object fieldValue = ReflectUtil.getFieldValue(bean, field);
        // 字段被忽略
        JpaSelectIgnoreField ignoreField = field.getAnnotation(JpaSelectIgnoreField.class);
        // 获取组装条件
        JpaSelectOperator wrapperOperator = field.getAnnotation(JpaSelectOperator.class);
        if (IObjects.nonNull(wrapperOperator)) {
            // wrapperOperator.connect().equals(SQLConnect.AND)
            // 默认 eq，且空值也查询
            SQLOperatorWrapper operator = IObjects.nonNull(wrapperOperator) ? wrapperOperator.operatorWrapper() : SQLOperatorWrapper.EQ;
            // 如果 值等于 list 则 使用 In 操作
            if (fieldValue instanceof Collection) {
                operator = SQLOperatorWrapper.IN;
            }
            // 使用自定义的名字
            if (!IObjects.isBlank(wrapperOperator.fieldName())) {
                fieldName = wrapperOperator.fieldName();
            }
            OperatorWrapper operatorWrapper = new OperatorWrapper(e, str2Path(e.getRoot()
                    , e.getBuilder()
                    , wrapperOperator.function()
                    , fieldName
            ), fieldValue);

            operator.consumer().accept(operatorWrapper);
        } else {
            // 没加查询注解的且没有被忽略的，默认设添加为 and  eq 查询条件 ， 且为空值就不查了
            // 构造 OperatorWrapper // 空值就不查了
            if (IObjects.nonNull(fieldValue)) {
                OperatorWrapper wrapper = new OperatorWrapper(e, str2Path(e.getRoot()
                        , e.getBuilder()
                        , SpecBuilderDateFun.NULL
                        , fieldName
                ), fieldValue);
                SQLOperatorWrapper.EQ.consumer().accept(wrapper);
            }
        }
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
    public static <R, B> Specification<R> beanWhere2(B bean, Consumer<SpecificationWrapper<R>> action) {
        Field[] fields = ReflectUtil.getFields(bean.getClass());
        Specification<R> rSpecification = Arrays.stream(fields)
                .filter(field -> {
                    JpaSelectOperator wrapperOperator = field.getAnnotation(JpaSelectOperator.class);
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
                    JpaSelectOperator wrapperOperator = field.getAnnotation(JpaSelectOperator.class);
                    // 字段值
                    Object fieldValue = ReflectUtil.getFieldValue(bean, field);
                    // 字段名
                    String fieldName = field.getName();
                    // 使用自定义的名字
                    if (!IObjects.isBlank(wrapperOperator.fieldName())) {
                        fieldName = wrapperOperator.fieldName();
                    }
                    // 字段名
                    String finalFieldName = fieldName;
                    Specification<R> where = Specifications.where(e -> {
                        // 默认 eq，且空值也查询
                        SQLOperatorWrapper operator = IObjects.nonNull(wrapperOperator) ? wrapperOperator.operatorWrapper() : SQLOperatorWrapper.EQ;
                        // 如果 值等于 list 则 使用 In 操作
                        if (fieldValue instanceof Collection) {
                            operator = SQLOperatorWrapper.IN;
                        }
                        // 构造 OperatorWrapper
                        OperatorWrapper wrapper = new OperatorWrapper(e, str2Path(e.getRoot()
                                , e.getBuilder()
                                , wrapperOperator.function()
                                , finalFieldName
                        ), fieldValue);
                        operator.consumer().accept(wrapper);
                    });
                    return where;
                }).reduce(Specification::and).orElse((root, criteriaQuery, criteriaBuilder) -> criteriaQuery.getRestriction());

        return rSpecification;

    }


    /**
     * 字符串的key名转jpa要用的对象
     */
    private static Expression str2Path(Root<?> root, CriteriaBuilder builder, SpecBuilderDateFun function, String fieldName) {
        Path<?> path;
        if (null != function && !function.equals(SpecBuilderDateFun.NULL)) {
            return JpaUtils.functionTimeFormat(function, root, builder, fieldName);
        }
        if (fieldName.contains(".")) {
            String[] names = fieldName.split("\\.");
            path = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                path = path.get(names[i]);
            }
        } else {
            path = root.get(fieldName);
        }
        return path;
    }

}
