package cn.jdevelops.build.controller;

import cn.jdevelops.annotation.mapping.PathRestController;
import cn.jdevelops.build.account.AccountService;
import cn.jdevelops.build.account.AuthUserVO;
import cn.jdevelops.build.account.SettingUserStatusDTO;
import cn.jdevelops.entity.basics.vo.SerializableVO;
import cn.jdevelops.jredis.service.RedisService;
import cn.jdevelops.jwt.annotation.NotRefreshToken;
import cn.jdevelops.jwt.constant.JwtConstant;
import cn.jdevelops.jwt.util.JwtUtil;
import cn.jdevelops.result.result.ResultVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户管理
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-07-18 13:42
 */
@PathRestController("user")
@Slf4j
@Api(tags = "用户管理", value = "用户管理")
@RequiredArgsConstructor
@ApiSupport(order = 0)
public class UserController {

    /**
     * 用户
     */
    private final AccountService accountService;

    private final RedisService redisService;




    @ApiOperation(value = "查询所有用户", notes = "用户管理")
    @PostMapping("finAll")
    @ApiOperationSupport(order = 4)
    public ResultVO<List<AuthUserVO>> findAll() {
        return ResultVO.success(SerializableVO.to(accountService.findAll(),AuthUserVO.class),"角色查询成功");
    }

    @ApiOperation(value = "查询时不刷新缓存", notes = "用户管理")
    @PostMapping("finAll2")
    @ApiOperationSupport(order = 4)
    @NotRefreshToken
    public ResultVO<List<AuthUserVO>> finAll2() {
        return ResultVO.success(SerializableVO.to(accountService.findAll(),AuthUserVO.class),"角色查询成功");
    }


    /**
     * 用户状态(1.正常 2.锁定 3.删除 4.非法)
     * @return ResultVO
     */
    @ApiOperation(value = "设置用户状态", notes = "用户管理")
    @PostMapping("settingUserStatus")
    @ApiOperationSupport(order = 4)
    public ResultVO<String> settingUserStatus(@RequestBody @Valid SettingUserStatusDTO settingUserStatus) {
        accountService.settingStatus(settingUserStatus.getUsername(),settingUserStatus.getStatus());
        return ResultVO.success();
    }

    @ApiOperation(value = "获取当前登录的用户信息", notes = "支持两种方式，1:CustomHeaderTokenSubjectCreator，" +
            "2:CustomPasswdSubjectCreator。一般能进来就是有效用户（此接口一定需要登录后才能访问，即最基础的权限")
    @PostMapping("getLoginUser")
    @ApiOperationSupport(order = 5)
    public ResultVO<AuthUserVO> getLoginUser(HttpServletRequest request){
        String token = request.getHeader(JwtConstant.TOKEN);
        if(StringUtils.isBlank(token)){
            return ResultVO.fail("请先登录");
        }else {
            // 可以考虑从redis中拿。但是每次修改用户相关的数据时都要更新一份到redis中
            return ResultVO.successForData(accountService.getUser(JwtUtil.getSubject(token)));
        }
    }

}
