package cn.tannn.apisign;

import cn.tannn.apisign.entity.UserEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiSignApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        UserEntity user = UserEntity.builder().age("123").name("谭宁").build();
        String xx = "{\n" +
                "    \"age\": \"3680530977946108\",\n" +
                "    \"name\": \"白霞\"\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(xx);
        System.out.println(jsonObject);
        System.out.println(jsonObject.toJSONString());
        System.out.println(JSON.toJSONString(user));
    }

}
