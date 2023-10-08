package cn.tan.authentication.sas.server.controller;

import cn.jdevelops.api.result.response.ResultVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  测试异常处理
 */
@RestController
public class MessagesController {


    /**
     * 登录就行
     */
    @GetMapping("/messages1")
    public ResultVO<String> getMessages1() {
        return ResultVO.successMessage(" hello Message 1");
    }


    /**
     * PreAuthorize(如果有指定权限, 则返回 true)
     */
    @GetMapping("/messages2")
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public  ResultVO<String> getMessages2() {
        return ResultVO.successMessage(" hello Message 2");
    }


    /**
     * PreAuthorize(如果有指定权限, 则返回 true)
     */
    @GetMapping("/messages3")
    @PreAuthorize("hasAuthority('SCOPE_Message1')")
    public  ResultVO<String> getMessages3() {
        return ResultVO.successMessage(" hello Message 3");
    }


}
