package cn.tannn.demo.renewpwd.dao;

import cn.tannn.demo.renewpwd.entity.TestDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2025/8/15 13:40
 */
public interface TestDemoDao extends JpaRepository<TestDemo, Integer>, JpaSpecificationExecutor<TestDemo> {
}
