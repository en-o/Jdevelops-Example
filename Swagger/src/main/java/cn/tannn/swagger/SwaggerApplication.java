package cn.tannn.swagger;

import cn.jdevelops.doc.core.swagger.bean.SwaggerBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

import static cn.jdevelops.doc.core.swagger.util.SwaggerUtil.*;
import static cn.jdevelops.doc.core.swagger.util.SwaggerUtil.securityContexts;

/**
 * @author tnnn
 */
@SpringBootApplication
public class SwaggerApplication {

    @Resource
    private SwaggerBean swaggerBean;

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }




}
