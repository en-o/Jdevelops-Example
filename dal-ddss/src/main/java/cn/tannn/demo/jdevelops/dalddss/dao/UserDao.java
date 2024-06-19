package cn.tannn.demo.jdevelops.dalddss.dao;


import cn.tannn.demo.jdevelops.dalddss.entity.nonExistentListener.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/20 13:17
 */
public interface UserDao  extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
