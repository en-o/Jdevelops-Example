package cn.tannn.jdevelops.demo.jpa.dao;

import cn.tannn.jdevelops.demo.jpa.entity.ConvertTest;
import cn.tannn.jdevelops.jpa.repository.JpaBasicsRepository;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/7/24 下午1:08
 */
public interface ConvertTestDao extends JpaBasicsRepository<ConvertTest, Long> {
    // error
    ConvertTest findByConfig(JSONObject config);
    // error - 查询不到
    @Query("select c from ConvertTest c where c.config = :config")
    ConvertTest findByConfigQuery(@Param("config") JSONObject config);

    // ok
    @Query("select c from ConvertTest c where FUNCTION('JSON_EXTRACT', c.config, :key) = :value")
    ConvertTest findByConfigQuery(@Param("key") String key, @Param("value") String value);
    @Query(value = "SELECT * FROM test_convert WHERE JSON_EXTRACT(config, :key) = :value", nativeQuery = true)
    ConvertTest findByConfigQuery2(@Param("key") String key, @Param("value") String value);


    // error
    ConvertTest findByStrs(List<String> strs);
    // ok
    @Query("select c from ConvertTest c where c.strs = :strs")
    ConvertTest findByStrsQuery(@Param("strs") List<String> strs);
}
