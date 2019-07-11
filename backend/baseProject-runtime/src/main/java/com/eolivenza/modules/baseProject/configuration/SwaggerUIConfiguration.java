package com.eolivenza.modules.baseProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@Profile(value = "!" + ProfileNames.PRODUCTION)
public class SwaggerUIConfiguration {

    public static final String AUTHORIZATION = "Authorization";

    @Bean
    public Docket docket() {

        List<ResponseMessage> defaultErrorList = Arrays.asList(
                new ResponseMessageBuilder().code(400).message("Bad Request.").build(),
                new ResponseMessageBuilder().code(404).message("Resource not found.").build(),
                new ResponseMessageBuilder().code(409).message("Conflict in the current state of the resource.").build(),
                new ResponseMessageBuilder().code(500).message("Internal Server Error.").build()
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, defaultErrorList)
                .globalResponseMessage(RequestMethod.POST, defaultErrorList)
                .globalResponseMessage(RequestMethod.PATCH, defaultErrorList)
                .globalResponseMessage(RequestMethod.DELETE, defaultErrorList)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eolivenza"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfo("baseProject REST Controllers",
                "baseProject API general summary...",
                "1.0",
                "termOfServiceUrl",
                new Contact("name",
                        "url",
                        "email"),
                "license",
                "licenseUrl",
                new ArrayList<>());
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION, AUTHORIZATION, "header");
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(null, null, null, // realm Needed for authenticate button to work
                null, // appName Needed for authenticate button to work
                "  ", // apiKeyValue
                ApiKeyVehicle.HEADER, AUTHORIZATION, // apiKeyName
                null);
    }
}