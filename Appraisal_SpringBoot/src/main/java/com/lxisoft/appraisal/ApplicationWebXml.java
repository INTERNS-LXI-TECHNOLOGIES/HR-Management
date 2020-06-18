package com.lxisoft.appraisal;

import io.github.jhipster.config.DefaultProfileUtil;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
@EnableSwagger2
/**
 * This is a helper Java class that provides an alternative to creating a {@code web.xml}.
 * This will be invoked only when the application is deployed to a Servlet container like Tomcat, JBoss etc.
 */
public class ApplicationWebXml extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // set a default to use when no profile is configured.
        DefaultProfileUtil.addDefaultProfile(application.application());
        return application.sources(AppraisalApp.class);
    }
    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .paths(PathSelectors.ant("/api/*"))
        .apis(RequestHandlerSelectors.basePackage("java.com.lxisoft.appraisal"))
        .build()
        .apiInfo(apiInfos());
    }
    private ApiInfo apiInfos(){
        return new ApiInfo(
            "HR- Management - Appraisal API",
            "Api  for Appraisal",
            "ver 1.0",
            "free API",
            new Contact("Admin","http:localhost/8080","hr@lxisof.com"),
            "API License",
            "http:localhost/8080",
            Collections.emptyList());


    }
}
