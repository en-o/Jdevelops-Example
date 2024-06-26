package cn.tannn.demo.jdevelops.authenticationsjwt.controller;

import cn.jdevelops.api.result.response.ResultVO;
import cn.jdevelops.authentication.jredis.service.RedisLoginService;
import cn.jdevelops.authentication.jwt.annotation.ApiPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限测试
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/8 13:55
 */
@RestController
public class RoleController {


    @Autowired
    private RedisLoginService redisLoginService;

    /**
     * "admin"
     */
    @GetMapping("/roles1")
    @ApiPermission(roles = {"admin"})
    public ResultVO<String> roles1(HttpServletRequest request) {
        return ResultVO.success("admin");
    }

    /**
     * "tan"
     */
    @GetMapping("/roles2")
    @ApiPermission(roles = {"tan"})
    public ResultVO<String> roles2(HttpServletRequest request) {
        return ResultVO.success("tan");
    }

    /**
     * "admin","tan"
     */
    @GetMapping("/roles3")
    @ApiPermission(roles = {"admin","tan"})
    public ResultVO<String> roles3(HttpServletRequest request) {
        return ResultVO.success("admin tan");
    }


    /**
     * permissions1
     */
    @GetMapping("/permissions1")
    @ApiPermission(permissions = "/permissions1")
    public ResultVO<Object> permissions1(HttpServletRequest request) {
        return ResultVO.success("permissions1");
    }


    /**
     * permissions2
     */
    @GetMapping("/permissions2")
    @ApiPermission(permissions = "/permissions2")
    public ResultVO<Object> permissions2(HttpServletRequest request) {
        return ResultVO.success("permissions2");
    }

}
