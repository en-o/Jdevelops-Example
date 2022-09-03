package cn.tannn.swagger.config;

import cn.jdevelops.doc.swagger.boot.core.SwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 自定义接口文档分类
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-09-02 09:28
 */
@Component
public class Knife4jConfig extends SwaggerConfig {

    @Bean(value = "groupapi1")
    public Docket apiGroup_1() {
        return createRestApi("聚合文档1", "cn.tannn.swagger.groupapi1");
    }


    @Bean(value = "groupapi2")
    public Docket apiGroup_2() {
        return createRestApi("聚合文档2", "cn.tannn.swagger.groupapi2");
    }

}
