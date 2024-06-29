package cn.tannn.jdevelops.demo.jpa.dao;

import cn.tannn.jdevelops.demo.jpa.entity.Address;
import cn.tannn.jdevelops.demo.jpa.entity.ExpressionRootDemo;
import cn.tannn.jdevelops.jpa.repository.JpaBasicsRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author <a href="https://t.tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/6/30 上午2:49
 */
public interface ExpressionRootDemoDao extends JpaBasicsRepository<ExpressionRootDemo, Long> {
    /**
     * 查询未知性别的用户
     * @return User
     */
    @Query("select o from ExpressionRootDemo o where o.gender = :#{gender} ")
    List<ExpressionRootDemo> findUnknownGender();

    /**
     * 查询 男性别的用户
     * @return User
     */
    @Query("select o from ExpressionRootDemo o where o.gender = :#{man} ")
    List<ExpressionRootDemo> findManGender();

    /**
     * 查询 女性别的用户
     * @return User
     */
    @Query("select o from ExpressionRootDemo o where o.gender = :#{woman} ")
    List<ExpressionRootDemo> findWomanGender();

}
