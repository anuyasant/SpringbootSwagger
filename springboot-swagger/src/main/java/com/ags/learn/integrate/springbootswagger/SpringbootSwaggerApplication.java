package com.ags.learn.integrate.springbootswagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class SpringbootSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSwaggerApplication.class, args);
	}

}
