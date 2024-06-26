package cn.tannn.demo.jdevelops.eventsredis;


import cn.tannn.jdevelops.events.redis.server.RedisReceiverServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Service;

/**
 * 自定义 项目里的订阅处理方式 - 默认控制台打印
 * @author tn
 * @date 2020-09-11 10:36
 */
@Service
public class CustomRedisReceiverServer implements RedisReceiverServer<String> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomRedisReceiverServer.class);
    /**
     * 消息接收
     * @param message message
     * @return String
     */
    @Override
    public String pubMessage(Message message) {
        LOG.info("\n----------------------------------------------------------\n\t" +
                "自定义 redis监听频道 "+new String(message.getChannel())+" 的消息\n\t" +
                "自定义 消息体："+new String(message.getBody()) + "\n" +
                "----------------------------------------------------------");
        return  message.toString();
    }
}
