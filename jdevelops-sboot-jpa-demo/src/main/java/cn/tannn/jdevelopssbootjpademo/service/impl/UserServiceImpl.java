package cn.tannn.jdevelopssbootjpademo.service.impl;

import cn.hutool.core.util.ReflectUtil;
import cn.jdevelops.data.jap.annotation.JpaSelectIgnoreField;
import cn.jdevelops.data.jap.annotation.JpaUpdate;
import cn.jdevelops.data.jap.exception.JpaException;
import cn.jdevelops.data.jap.service.impl.J2ServiceImpl;
import cn.jdevelops.data.jap.util.IObjects;
import cn.tannn.jdevelopssbootjpademo.controller.pojo.UpdateUser;
import cn.tannn.jdevelopssbootjpademo.dao.UserDao;
import cn.tannn.jdevelopssbootjpademo.entity.User;
import cn.tannn.jdevelopssbootjpademo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;


/**
 * 用户表
 *
 * @author lxw
 * @date 2021-09-10 11:41
 */
@Slf4j
@Service
public class UserServiceImpl extends J2ServiceImpl<UserDao, User, Integer> implements UserService {


    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {
        super(User.class);
    }


    @Override
    public User findByUserNoCopyDao(String userNo) {
        return userDao.findByUserNo(userNo);
    }

    @Override
    public User findByUserNoCopy2Dao(String userNo) {
        return getJpaBasicsDao().findByUserNo(userNo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBeanTest(UpdateUser user, String uniqueKey) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<User> update = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> deleteFrom = update.from(User.class);

        // 忽略字段
        Field[] fields = ReflectUtil.getFields(user.getClass(),(field) -> {
            JpaUpdate ignoreField = field.getAnnotation(JpaUpdate.class);
            return ignoreField == null || !ignoreField.ignore();
        });
        // 获取主键名
        Metamodel metamodel = getEntityManager().getMetamodel();
        EntityType<User> entityType = metamodel.entity(User.class);
        SingularAttribute<? super User, ?> id = entityType.getId(entityType.getIdType().getJavaType());
        Predicate condition;
        String ignoreField ;
        if (IObjects.isBlank(uniqueKey)) {
            ignoreField = id.getName();
            // 根据主键更新
            condition = criteriaBuilder.equal(deleteFrom.get(id.getName()), ReflectUtil.getFieldValue(user, id.getName()));
        } else {
            // 根据传入的唯一key键
            ignoreField = uniqueKey;
            condition = criteriaBuilder.equal(deleteFrom.get(uniqueKey), ReflectUtil.getFieldValue(user, uniqueKey));
        }

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            // 字段名
            String fieldName = field.getName();
            // 字段值
            Object fieldValue = ReflectUtil.getFieldValue(user, field);
            if (fieldValue != null && !fieldName.equals(ignoreField)) {
                // 设置更新值
                update.set(deleteFrom.get(fieldName), fieldValue);
            }
        }
        // 应用更新的条件
        update.where(condition);
        // 执行更新
        int i = getEntityManager().createQuery(update).executeUpdate();
        log.info("更新："+i);
    }
}
