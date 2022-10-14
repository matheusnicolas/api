package com.lofi.learning.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 19 and Spring Boot 3.0.0-M5")
                        .version("v1")
                        .description("Some description about your API.")
                        .termsOfService("https://pub.api.com")
                        .license(new License().name("Apache 2.0")
                                .url("https://pub.api.com")));
    }
}
