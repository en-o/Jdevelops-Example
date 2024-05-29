//package cn.tannn.delayredis.service;
//
//import cn.tannn.jdevelops.delays.core.entity.DelayQueueMessage;
//import cn.tannn.jdevelops.delays.core.factory.DelayFactory;
//import cn.tannn.jdevelops.delays.core.service.DelayService;
//import cn.tannn.jdevelops.delays.redis.RedisDelayService;
//import com.alibaba.fastjson2.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.SessionCallback;
//import org.springframework.data.redis.core.ZSetOperations;
//import org.springframework.data.redis.core.script.DefaultRedisScript;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.ScheduledThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 重构内置的 JdkDelayService
// * @author tnnn
// * @version V1.0
// * @date 2023-01-05 16:34
// */
//@Service
//@Slf4j
//public class RefactorRedisDelayService implements DelayService<DelayQueueMessage> {
//
//    private static final Logger logger = LoggerFactory.getLogger(RefactorRedisDelayService.class);
//
//    @Resource
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Resource
//    private DelayFactory<DelayQueueMessage> delayRunFactory;
//
//    /**
//     * 延时队列的key
//     */
//    private static final String DELAY_QUEUE ="delay:redis_delay_queue";
//
//    /**
//     * redis 脚本
//     */
//    @Resource
//    private DefaultRedisScript<List<String>> delayRedisScript;
//
//
//    /**
//     * 线程池
//     */
//    private static final String NAME = "RedisDelayMessageTask-thread-";
//    private final AtomicInteger seq = new AtomicInteger(1);
//    private final ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1, r ->
//            new Thread(r, NAME + seq.getAndIncrement()));
//
//
//    @Override
//    public void produce(DelayQueueMessage delayMessage) {
//        //生产者把消息丢到消息队列中
//        //序列化
//        String value = JSON.toJSONString(delayMessage);
//        // 有序
//        redisTemplate.opsForZSet().add(DELAY_QUEUE, value, delayMessage.getDelayTime());
//
//    }
//
//    @Override
//    public void produce(List<DelayQueueMessage> delayMessage) {
//        delayMessage.forEach(message -> {
//            produce(message);
//        });
//    }
//
//    @Override
//    public void cancel(String delayMessage) {
//        logger.warn("===> redis delete delay  at risk , new  data may be lost, delete value: {}", delayMessage);
//        // 获取有序集合操作对象
//        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
//        // 开启事务
//        SessionCallback<Object> callback = new SessionCallback<Object>() {
//            @Override
//            public Object execute(RedisOperations operations) throws DataAccessException {
//                operations.multi(); // 标记事务开始
//                zSetOps.remove(DELAY_QUEUE, delayMessage);
//                // 执行其他操作...
//                return operations.exec(); // 提交事务
//            }
//        };
//        redisTemplate.execute(callback);
//    }
//
//    @Override
//    public void cancel(DelayQueueMessage delayMessage) {
//        String value = JSON.toJSONString(delayMessage);
//        cancel(value);
//    }
//
//
//    @Override
//    public void consumeDelay() {
//        // IllegalArgumentException 的话 initialDelay = 1， period = 1 直接写死
//        // 初始化
//        long initialDelay =Math.round(Math.random()*10+10);
//        // 周期 周期小于或等于零时会抛异常
//        long period = Math.round(Math.random()*10);
//        logger.info("重构，开始消费延时队列数据...");
//        pool.scheduleAtFixedRate(()->{
//            try {
//                Set<String> set = runLuaScript(DELAY_QUEUE);
//                if (!CollectionUtils.isEmpty(set)){
//                    set.forEach(s -> {
//                        DelayQueueMessage redisDelayMessage = JSON.to( DelayQueueMessage.class,JSON.parseObject(s));
//                        delayRunFactory.delayExecute(redisDelayMessage);
//                    });
//                }
//            }catch (Throwable e){
//                logger.error("RemindMessageTask error..",e);
//            }
//        }, initialDelay,period, TimeUnit.SECONDS);
//    }
//
//    /**
//     * 运行lua脚本
//     * @param key 延时队列的key
//     * @return 返回数据
//     */
//    public Set<String> runLuaScript(String key) {
//        double min =  0;
//        double max = System.currentTimeMillis();
//        List<String> execute = redisTemplate.execute(delayRedisScript, Collections.singletonList(key), min, max);
//        return new HashSet<>(execute);
//    }
//}
