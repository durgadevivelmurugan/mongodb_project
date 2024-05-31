package com.mongo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("com.mongo")
@EntityScan("com.mongo.entity")
@EnableMongoRepositories("com.mongo.repository")
public class NewMongoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewMongoProjectApplication.class, args);
	}

}
