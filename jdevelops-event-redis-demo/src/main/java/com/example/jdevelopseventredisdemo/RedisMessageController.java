package com.example.jdevelopseventredisdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * redis发布消息
 * @author tn
 * @ClassName RedisController
 * @description redis接口
 * @date 2020-09-30 12:04
 */
@RequestMapping("redis")
@RestController
public class RedisMessageController {

    private static final Logger log = LoggerFactory.getLogger(RedisMessageController.class);

    @Autowired
    StringRedisTemplate template;

    /**
     * 给频道 tn 发送消息
     * @return
     */
    @GetMapping(value = "/syncmessage")
    public String SyncMessage(){
        for(int i = 1; i <= 5; i++){
            try{
                // 为了模拟消息，sleep一下。
                Thread.sleep(2000);
            }catch(InterruptedException ex){}
            template.convertAndSend("tn", String.format("tn - 我是消息{%d}号: %tT", i, new Date()));
        }

        return "success";
    }


    /**
     * 给频道 test 发送消息
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("setMessage")
    public String setMessage() throws JsonProcessingException {
        Map map = new HashMap();
        map.put("name","你好");
        map.put("sex","2");
        map.put("time",new Date());
        template.convertAndSend("test", new ObjectMapper().writeValueAsString(map));
        return "success";
    }

}
