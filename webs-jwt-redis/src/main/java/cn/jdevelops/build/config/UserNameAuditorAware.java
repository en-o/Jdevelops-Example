package cn.jdevelops.build.config;


import cn.jdevelops.build.util.UserTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 *  JPA完成审计功能  使用时在自己的像中吧这个复制过去
 * @author tn
 * @version 1
 * eg: Application启动类要加注解: @EnableJpaAuditing
 * 监听
 * 自动注入用户名
 *
 *
 *  其中泛型T可以为String保存用户名，也可以为Long/Integer保存用户ID
 *  正式项目中需要从用户权限模块中获取到当前登录的用户信息
 *
 * @date 2020/5/26 22:29
 */
@Component
@Slf4j
public class UserNameAuditorAware implements AuditorAware<String> {

    @Resource
    private HttpServletRequest request;

    @NotNull
    @Override
    public Optional<String> getCurrentAuditor() {
        // 自己重新构建
        try {
            String tokenInfo = UserTokenUtil.getLoginNameByToke(request);
            return Optional.of(tokenInfo);
        } catch (Exception e) {
            log.error("自动填充数据创建者时获取当前登录用户的loginName失败");
        }
        return Optional.of("administrator");
    }

}
