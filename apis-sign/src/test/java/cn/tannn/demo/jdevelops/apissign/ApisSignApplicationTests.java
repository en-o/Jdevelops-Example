package cn.tannn.demo.jdevelops.apissign;

import cn.tannn.demo.jdevelops.apissign.entity.UserEntity;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ApisSignApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void test() {
        UserEntity user = UserEntity.builder().age("123").name("谭宁").build();
        String xx = "{\n" +
                "    \"age\": \"3680530977946108\",\n" +
                "    \"name\": \"白霞\"\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(xx);
        assertEquals("{\"age\":\"3680530977946108\",\"name\":\"白霞\"}", jsonObject.toJSONString());
        assertEquals("{\"age\":\"123\",\"name\":\"谭宁\"}", JSON.toJSONString(user));
    }

}
