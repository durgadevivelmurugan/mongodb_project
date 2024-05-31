package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("com.example")
@EntityScan("com.example.entity")
@EnableMongoRepositories("com.example.repository")
public class NewMongoDb1Application {

	public static void main(String[] args) {
		SpringApplication.run(NewMongoDb1Application.class, args);
	}

}
