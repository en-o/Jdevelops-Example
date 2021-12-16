package cn.tannn.websocket.service;

/**
 * @author tn
 * @ClassName AlarmWebSocketServer
 * @description 自定义消息发送接口
 * @date 2020-12-24 15:58
 */
public interface AlarmWebSocketServer {

    /**
     *  使用 sendInfoByLikeKey 进行模糊匹配用户进行消息发送
     *  匹配  keyPrefix 开头的 websocket 用户 给他们发送消息 （keyPrefix用户1, keyPrefix用户2）
     *  @param keyPrefix 主键前缀
     *  @param message 消息
     */
    public void sendInfoByLikeKey(String keyPrefix, String message);
}