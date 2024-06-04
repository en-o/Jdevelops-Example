package com.example.jdevelopswebswebsocketdemo;

import cn.jdevelops.sboot.websocket.core.SocketController;
import cn.jdevelops.util.jwt.core.JwtService;
import cn.jdevelops.webs.websocket.core.WebSocketServer;
import org.jose4j.lang.JoseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tn
 * @ClassName WebSocketController
 * @description websocket接口
 * @date 2020-12-24 15:53
 */
@RestController
@RequestMapping("websocket")
public class TanWebSocketController extends SocketController {


    public TanWebSocketController(WebSocketServer webSocketServer) {
        super(webSocketServer);
    }

    /**
     * 测试接口是否通畅
     * @return
     */
    @RequestMapping("/test01")
    public String testo01(){
        return "websocket";
    }


    /**
     * 测试接口是否通畅
     * @return
     */
    @RequestMapping("/getToken")
    public String getToken() throws JoseException {
        return  JwtService.generateToken("tan");

    }



}
