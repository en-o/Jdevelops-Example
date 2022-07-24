package cn.tannn.jwt.config;

import cn.jdevelops.jwt.util.JwtUtil;
import cn.jdevelops.jwtweb.server.impl.DefaultInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * 自定义登录验证实现
 *
 * @author tn
 * @className JWTCheckTokenInterceptorImpl
 * @date 2021-10-11 09:28
 */
@Service
@Primary
@Slf4j
public class JWTCheckTokenInterceptorImpl extends DefaultInterceptor {

    @Override
    public boolean checkToken(String token) {
      try {
          // 验证token有效期
          return JwtUtil.verity(token);
      }catch (Exception e){
          return false;
      }
    }
}
