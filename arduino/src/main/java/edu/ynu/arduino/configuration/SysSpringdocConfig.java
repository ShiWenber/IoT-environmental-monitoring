package edu.ynu.arduino.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//knife4j使用openapi3风格
@Configuration
public class SysSpringdocConfig {

    @Bean
    public GroupedOpenApi sysPlatformApiDoc() {
        return GroupedOpenApi.builder()
                .group("sysPlatform-controller")
                .packagesToScan("edu.ynu.arduino.controller")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("arduino数据监控管理")
                        .version("1.0.0")
                        .description("数据管理平台")
                        .termsOfService("http://doc.xiaominfo.com")
                        .license(new License().name("Apache 2.0")
                                .url("http://doc.xiaominfo.com"))
                );
    }


}
