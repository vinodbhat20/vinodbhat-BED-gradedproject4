package com.vb.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vb.app")
public class GradedProject4Application {

	public static void main(String[] args) {
		SpringApplication.run(GradedProject4Application.class, args);
	}

}
