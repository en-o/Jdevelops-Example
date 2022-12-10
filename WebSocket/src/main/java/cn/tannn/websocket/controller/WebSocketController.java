package cn.tannn.websocket.controller;

import cn.jdevelops.jwt.util.JwtUtil;
import cn.jdevelops.websocket.client.controller.SocketController;
import cn.jdevelops.websocket.core.service.WebSocketServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author tn
 * @ClassName WebSocketController
 * @description websocket接口
 * @date 2020-12-24 15:53
 */
@RestController
@RequestMapping("websocket")
public class WebSocketController extends SocketController {


    public WebSocketController(WebSocketServer webSocketServer) {
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
    public String getToken(){
        return  JwtUtil.sign("tan", Collections.singletonMap("sex","男"));

    }



}
