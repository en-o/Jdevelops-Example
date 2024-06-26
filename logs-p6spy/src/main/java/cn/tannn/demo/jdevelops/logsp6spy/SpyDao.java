package cn.tannn.demo.jdevelops.logsp6spy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @date 2024/6/26 上午11:48
 */
public interface SpyDao extends JpaRepository<Spy, Integer> {

    @Modifying
    @Transactional
    int deleteByName(String name);

    List<Spy> findByName(String name);

}
