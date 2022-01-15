package it.manuelgozzi.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableMongoRepositories
public class ManuelGozziBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManuelGozziBlogApplication.class, args);
	}
}
