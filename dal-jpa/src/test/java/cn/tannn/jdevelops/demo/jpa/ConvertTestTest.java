package cn.tannn.jdevelops.demo.jpa;

import cn.tannn.jdevelops.demo.jpa.dao.ConvertTestDao;
import cn.tannn.jdevelops.demo.jpa.entity.ConvertTest;
import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * 内置convert测试
 *
 * @author <a href="https://t.tannn.cn/">tan</a>
 * @version V1.0
 * @date 2024/7/24 下午1:08
 * @see https://www.baeldung.com/jpa-attribute-converters
 */

@SpringBootTest
public class ConvertTestTest {

    @Autowired
    ConvertTestDao convertTestDao;

    static JSONObject json = JSONObject.of("test", "s");
    static List<String> strs = Arrays.asList("as", "da");

    @Test
    void add() {
        ConvertTest test = new ConvertTest();
        test.setConfig(json);
        test.setStrs(strs);
        ConvertTest save = convertTestDao.save(test);
        System.out.println("save: " + save);
    }

    @Test
    void finds() {
        convertTestDao.findAll().forEach(System.out::println);
    }

    @Test
    void find() {
        System.out.println("findByStrs==> " + convertTestDao.findByStrsQuery(strs));
        System.out.println("findByConfig1==> " + convertTestDao.findByConfigQuery("$.test", "s"));
        System.out.println("findByConfig2==> " + convertTestDao.findByConfigQuery2("$.test", "s"));

        // 这样查询不到
        System.out.println("findByConfig3==> " + convertTestDao.findByConfigQuery(json));


        // 错误的示例
//        System.out.println("findByStrs==> " + convertTestDao.findByStrs(strs));
//        System.out.println("findByConfig==> " + convertTestDao.findByConfig(json));
    }

}
