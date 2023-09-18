package com.spring.reactive.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:env.properties")
public class ReactiveWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveWebApplication.class, args);
	}

}
