package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(scanBasePackages = { "com.controller" })
@EnableMongoRepositories(basePackages = "com.repository")
public class DemoApplication {

  private final Logger log = LoggerFactory.getLogger(DemoApplication.class);
  
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
