package com.example.webdemo.config;

import cn.jdevelops.sboot.authentication.jredis.entity.sign.RedisSignEntity;
import cn.jdevelops.sboot.authentication.jredis.util.RsJwtWebUtil;
import cn.jdevelops.sboot.jpa.core.auditor.AuditorNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author tan
 */
@Component
@Slf4j
public class TokenAuditorNameServiceImpl implements AuditorNameService {

    @Resource
    private HttpServletRequest request;

    @Override
    public Optional<String> settingAuditorName() {
        // 自己重新构建
        try {
            RedisSignEntity<String> signEntity = RsJwtWebUtil.getTokenByRedisSignEntity(request,String.class);
            return Optional.of(signEntity.getLoginName());
        } catch (Exception e) {
            log.error("自动填充数据创建者时获取当前登录用户的loginName失败");
        }
        return Optional.of("administrator");
    }
}
