This sample shows how to use [azure app configuration](https://docs.microsoft.com/en-us/azure/azure-app-configuration/) to store cosmosdb configuration and refresh cosmosdb configuration automatically.

Refer [Spring Doc](https://docs.microsoft.com/en-us/azure/azure-app-configuration/quickstart-java-spring-app) for more details.


Go to branch [keyvault-refresh](https://github.com/sophiaso/spring-cosmosdb-refresh/tree/keyvault-refresh) to check how to refresh with key vault.

### Preparation

1. Create cosmosdb

2. Create Key Vault store, create `my-cosmosdb-key` secret with the value of cosmosdb primary key

3. Update properties in the application.properties, replace all the `{xyz-abc}` formatted properties, keep the `azure.cosmosdb.key=${my.cosmosdb.key}` as-is.
```propertie
azure.keyvault.client-id={your-keyvault-client-id}
azure.keyvault.client-key={your-keyvault-secret-id}
azure.keyvault.uri={your-keyvault-uri}

azure.cosmosdb.database={replace-with-your-database-name}
# Do not change below line, create my-cosmosdb-key in keyvault
azure.cosmosdb.key=${my.cosmosdb.key} 
azure.cosmosdb.uri={replace-with-your-database-uri}

management.endpoints.web.exposure.include=*
```


### Run the application

Start the Spring Boot application `CosmosdbRefreshApplication`.

Access `localhost:8080` to load user data.

Check more APIs under the `UserController`.

### Check the refresh

1. Regenerate the primary key in cosmosdb
 
2. Send GET request localhost:8080 again, should fail, as the primary key is no longer valid

3. Update the key vault secret `my-cosmosdb-key` with the new version of primary key, to avoid messy, disable the old version of this secret

4. Send POST request `localhost:8080/actuator/refresh`

5. Send GET request localhost:8080 again, should see below logs and the request returns successfully
```
... c.e.cosmosdbrefresh.CosmosConfiguration  : Creating DocumentDBConfig, database key: [xxxx ].
... c.e.cosmosdbrefresh.CosmosConfiguration  : Creating DocumentDbFactory with database key: [xxxx ]
... c.m.azure.documentdb.DocumentClient      : Initializing DocumentClient with serviceEndpoint [xxx.....
```

