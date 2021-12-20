package com.jungdam.common.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String PROJECT_NAME = "JungDam 정담";
    private static final String GITHUB_URL = "https://github.com/jung-dam-diary";
    private static final String DEVELOPER_EMAIL = "wrjssmjdhappy@gmail.com";
    private static final String API_TITLE = "JungDam API";
    private static final String API_DESCRIPTION = "Management REST API SERVICE";
    private static final String API_VERSION = "1.0";
    private static final String TERMS_OF_SERVICE_URL = "urn:tos";
    private static final String APACHE_LICENSE = "Apache 2.0";
    private static final String APACHE_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0";
    private static final String CONTEXT_TYPE_JSON = "application/json";
    private static final String CONTEXT_TYPE_XML = "application/xml";
    private static final String API_NAME_JWT = "JWT";
    private static final String API_KEYNAME = "Authorization";
    private static final String API_PASS_AS = "header";
    private static final String AUTHORIZATION_SCOPE = "global";
    private static final String AUTHORIZATION_DESCRIPTION = "accessEverything";
    private static final int SCOPE_INIT = 0;
    private static final int SCOPE_SIZE = 1;

    private static final Contact DEFAULT_CONTACT = new Contact(
        PROJECT_NAME,
        GITHUB_URL,
        DEVELOPER_EMAIL
    );

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
        API_TITLE,
        API_DESCRIPTION,
        API_VERSION,
        TERMS_OF_SERVICE_URL,
        DEFAULT_CONTACT,
        APACHE_LICENSE,
        APACHE_LICENSE_URL,
        new ArrayList<>()
    );

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
        Arrays.asList(CONTEXT_TYPE_JSON, CONTEXT_TYPE_XML)
    );


    private ApiKey apiKey() {
        return new ApiKey(API_NAME_JWT, API_KEYNAME, API_PASS_AS);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(AUTHORIZATION_SCOPE,
            AUTHORIZATION_DESCRIPTION);
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[SCOPE_SIZE];
        authorizationScopes[SCOPE_INIT] = authorizationScope;
        return List.of(new SecurityReference(API_NAME_JWT, authorizationScopes));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(DEFAULT_API_INFO)
            .produces(DEFAULT_PRODUCES_AND_CONSUMES)
            .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
            .securityContexts(List.of(securityContext()))
            .securitySchemes(List.of(apiKey()));
    }
}