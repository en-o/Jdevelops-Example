package com.example.jdevelopsapisigndemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jdevelopsapisigndemo.entity.UserEntity;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JdevelopsApiSignDemoApplicationTests {

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
