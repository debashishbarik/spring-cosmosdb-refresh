package com.example.cosmosdbrefresh;

import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.spring.data.cosmosdb.DocumentDbFactory;
import com.microsoft.azure.spring.data.cosmosdb.config.AbstractDocumentDbConfiguration;
import com.microsoft.azure.spring.data.cosmosdb.config.DocumentDBConfig;
import com.microsoft.azure.spring.data.cosmosdb.core.DocumentDbOperations;
import com.microsoft.azure.spring.data.cosmosdb.core.DocumentDbTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CosmosConfiguration extends AbstractDocumentDbConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(CosmosConfiguration.class);

    @Autowired
    private DocumentDbProperties properties;

    @Bean
    @RefreshScope
    public DocumentDBConfig getConfig() {
        LOGGER.info("Creating DocumentDBConfig, database key: [{}].", properties.getKey());
        return DocumentDBConfig.builder(properties.getUri(), properties.getKey(), properties.getDatabase()).build();
    }

    @Bean
    @Primary
    @RefreshScope
    public DocumentDbFactory myDocumentDbFactory(DocumentDBConfig config) {
        LOGGER.info("Creating DocumentDbFactory with database key: [{}]", config.getKey());
        return new DocumentDbFactory(config);
    }

    @Bean
    @Primary
    @RefreshScope
    public DocumentDbOperations documentDbTemplate(DocumentDBConfig config) throws ClassNotFoundException {
        return new DocumentDbTemplate(this.myDocumentDbFactory(config), this.mappingDocumentDbConverter(),
                config.getDatabase());
    }
}
