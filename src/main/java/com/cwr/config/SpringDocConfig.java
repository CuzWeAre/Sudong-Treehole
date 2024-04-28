package com.cwr.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("速冻树洞API")
                        .description("一个轻量化的校园树洞")
                        .version("v0.1")
                        .license(new License()
                                .name("All rights reserved")
                                .url(""))
                        .contact(new Contact()
                                .name("CuzWeAre")
                                .url("https://github.com/CuzWeAre")))
                .externalDocs(new ExternalDocumentation()
                        .description("CuzWeAre's Blog")
                        .url("https://www.whoa.world"));
    }
}