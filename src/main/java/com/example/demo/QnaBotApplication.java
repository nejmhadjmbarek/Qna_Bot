package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.bot" })
public class QnaBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(QnaBotApplication.class, args);
	}
}
