package cn.tannn.demo.jdevelops.swagger.apisknife4j.config;

import cn.tannn.jdevelops.knife4j.core.entity.BuildSecuritySchemes;
import cn.tannn.jdevelops.knife4j.domain.SwaggerProperties;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static cn.tannn.jdevelops.knife4j.core.util.SwaggerUtil.buildSecuritySchemes;


/**
 * 接口分组
 *
 * @author tnnn
 * @version V1.0
 * @date 2023-03-12 21:05
 */
@Component
public class GroupedOpenApis {
    @Bean
    public GroupedOpenApi api2(SwaggerProperties swaggerProperties){
        // 加入  Authorize
        BuildSecuritySchemes buildSecuritySchemes = buildSecuritySchemes(swaggerProperties.getSwaggerSecuritySchemes());
        //  /** 表示 packagesToScan下的所有接口
        String[] pathsToMatch = { "/**" };
        // 需要扫描的包路径
        String[] packagesToScan = {"cn.tannn.springbootparentswagger.groups.groupapi1"};
        return GroupedOpenApi.builder().group("api2")
                .pathsToMatch(pathsToMatch)
                // todo: Authorize 未生效，请求header里未包含参数 - 临时处理方法
                .addOperationCustomizer((operation, handlerMethod) -> operation.security(buildSecuritySchemes.getSecurityItem()))
                .packagesToScan(packagesToScan).build();
    }

    @Bean
    public GroupedOpenApi api3(SwaggerProperties swaggerProperties){
        BuildSecuritySchemes buildSecuritySchemes = buildSecuritySchemes(swaggerProperties.getSwaggerSecuritySchemes());
        String[] pathsToMatch = { "/**" };
        String[] packagesToScan = {"cn.tannn.springbootparentswagger.groups.groupapi2"};
        return GroupedOpenApi.builder().group("api3")
                .pathsToMatch(pathsToMatch)
                // todo: Authorize 未生效，请求header里未包含参数 - 临时处理方法
                .addOperationCustomizer((operation, handlerMethod) -> operation.security(buildSecuritySchemes.getSecurityItem()))
                .packagesToScan(packagesToScan).build();
    }
}
