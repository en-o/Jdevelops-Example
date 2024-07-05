package cn.tannn.demo.frameworksfile.test.dao;


import cn.tannn.demo.frameworksfile.test.entity.TestWeb;
import cn.tannn.jdevelops.jpa.repository.JpaBasicsRepository;

/**
 * @author <a href="https://t.tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/6/30 上午1:27
 */
public interface TestDao extends JpaBasicsRepository<TestWeb, Long> {
}
