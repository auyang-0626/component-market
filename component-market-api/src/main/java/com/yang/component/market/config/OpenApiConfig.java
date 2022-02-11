package com.yang.component.market.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * @author yangguangyue
 * Created on 2021-12-20
 */
public class OpenApiConfig {

    @Bean
    public OpenAPI initOpenAPI() {
        return new OpenAPI().info(
                new Info().title("app factory api").description("OpenAPI").version("v1.0")
        );
    }
}
