package cn.tannn.springbootparentswagger;

import cn.jdevelops.sboot.swagger.config.MultipleGroupConfig;
import cn.jdevelops.sboot.swagger.core.entity.BuildSecuritySchemes;
import cn.jdevelops.sboot.swagger.domain.SwaggerProperties;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static cn.jdevelops.sboot.swagger.core.util.SwaggerUtil.basePackages;
import static cn.jdevelops.sboot.swagger.core.util.SwaggerUtil.buildSecuritySchemes;

@SpringBootApplication
public class SpringbootParentSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootParentSwaggerApplication.class, args);
    }


}
