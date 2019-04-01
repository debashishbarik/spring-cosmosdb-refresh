package com.example.cosmosdbrefresh;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "azure.cosmosdb")
public class DocumentDbProperties {

    private String uri;

    private String key;

    private String database;
}
