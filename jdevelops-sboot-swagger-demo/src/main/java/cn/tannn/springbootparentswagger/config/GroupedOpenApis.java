package cn.tannn.springbootparentswagger.config;

import cn.jdevelops.sboot.swagger.config.SwaggerProperties;
import cn.jdevelops.sboot.swagger.core.entity.BuildSecuritySchemes;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static cn.jdevelops.sboot.swagger.core.util.SwaggerUtil.basePackages;
import static cn.jdevelops.sboot.swagger.core.util.SwaggerUtil.buildSecuritySchemes;

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
        BuildSecuritySchemes buildSecuritySchemes = buildSecuritySchemes(swaggerProperties);
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
        BuildSecuritySchemes buildSecuritySchemes = buildSecuritySchemes(swaggerProperties);
        String[] pathsToMatch = { "/**" };
        String[] packagesToScan = {"cn.tannn.springbootparentswagger.groups.groupapi2"};
        return GroupedOpenApi.builder().group("api3")
                .pathsToMatch(pathsToMatch)
                // todo: Authorize 未生效，请求header里未包含参数 - 临时处理方法
                .addOperationCustomizer((operation, handlerMethod) -> operation.security(buildSecuritySchemes.getSecurityItem()))
                .packagesToScan(packagesToScan).build();
    }
}
