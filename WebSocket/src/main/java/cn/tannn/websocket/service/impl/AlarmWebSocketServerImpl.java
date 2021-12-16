package cn.tannn.websocket.service.impl;

import cn.jdevelops.websocket.core.service.WebSocketServer;
import cn.tannn.websocket.service.AlarmWebSocketServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tn
 * @ClassName AlarmWebSocketServerImpl
 * @description 自定义消息发送接口
 * @date 2020-12-24 15:59
 */
@Service
public class AlarmWebSocketServerImpl implements AlarmWebSocketServer {

    @Resource
    private WebSocketServer webSocketServer;

    @Override
    public void sendInfoByLikeKey(String keyPrefix, String message) {
//        匹配  keyPrefix 开头的 websocket 用户 给他们发送消息 （keyPrefix用户1, keyPrefix用户2）
        webSocketServer.sendInfoByLikeKey(keyPrefix,message);
    }
}