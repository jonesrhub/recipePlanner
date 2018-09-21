package com.service.demo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;

@ComponentScan("com")
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext =
			SpringApplication.run(DemoApplication.class, args);
	}
}
