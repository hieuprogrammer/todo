package dev.tech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class OpenApiConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("dev.tech.web.api"))
                .build().apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "TO-DOs",
                "Todo Web Application and REST API",
                "1.0",
                "",
                new Contact("Hieu Minh Le", "", "hieu.minhle@outlook.com"),
                "Developed by Hieu Minh Le","", Collections.emptyList()
        );
    }
}