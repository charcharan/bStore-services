package com.bteam.bstore.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {   
	
	@Value("#{'${aws.api.basePackage}'}")
	private String apiBasePackage;	
	
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .pathMapping(AppConstants.FORWARD_SLASH)
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage(apiBasePackage))              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
}