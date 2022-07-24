package cn.jdevelops.build.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

/**
 * actuator接口鉴权
 * @author web
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * https://mlog.club/article/1565206
     */
        @Value("${actuator.security.endpoints:#{null}}")
    private String endpoints;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        方式1：开放的actuator端点全部都验证
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
                .anyRequest().authenticated().and().httpBasic();
//        方式2：仅验证名单中的actuator端点
//        http.requestMatcher(EndpointRequest.to(transformEndpoints(endpoints))).authorizeRequests()
//                .anyRequest().authenticated().and().httpBasic();
    }

    private String[] transformEndpoints(String endpoints) {
        // isEmpty判空方法
        if (isEmpty(endpoints)) {
            return new String[0];
        }
        return endpoints.split(",");

    }


}
