package com.example.demo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
//@EnableSwagger2
public class WasherApplication {
	
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(WasherApplication.class, args);
	}
	
//	@Bean
//	public Docket SwaggerConfig(){
//		//Returns a prepared docket instance
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
//				.paths(PathSelectors.any())
//				.build()
//				.apiInfo(apiInfo());
//			
//
//	}
//	@SuppressWarnings({ "unused", "deprecation" })
//	private ApiInfo apiInfo(){
//		return new ApiInfoBuilder()
//				.title("Waasher Service")
//				.description("This service uses another services for help and provides endpoints for the washer")
//				.contact("nikhil7singh02@gmail.com")
//				.version("1.0.0")
//				.build();
//	}
	 

}
