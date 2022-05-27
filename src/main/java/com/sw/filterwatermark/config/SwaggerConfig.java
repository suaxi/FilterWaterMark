package com.sw.filterwatermark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * @author Suaxi
 * @date 2022/5/27 17:41
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sw"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "FilterWaterMark",
                "FilterWaterMarkApi",
                "1.0.0",
                null,
                new Contact("Suaxi", null, null),
                "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0.html",
                Collections.emptyList()
        );
    }
}
