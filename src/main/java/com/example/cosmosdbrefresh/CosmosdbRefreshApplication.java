package com.example.cosmosdbrefresh;

import com.microsoft.azure.spring.data.cosmosdb.repository.config.EnableDocumentDbRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@EnableDocumentDbRepositories
@SpringBootApplication
@EnableConfigurationProperties(DocumentDbProperties.class)
public class CosmosdbRefreshApplication {
	@Autowired
	private UserRepository repository;

	private static final User user = new User("1", "user1@sample.com", "user one");

	public static void main(String[] args) {
		SpringApplication.run(CosmosdbRefreshApplication.class, args);
	}

	@PostConstruct
	public void setup() {
		this.repository.save(user);
	}

	@PreDestroy
	public void cleanup() {
		this.repository.deleteAll();
	}
}
