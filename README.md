This sample shows how to use [azure app configuration](https://docs.microsoft.com/en-us/azure/azure-app-configuration/) to store cosmosdb configuration and refresh cosmosdb configuration automatically.

Refer [Spring Doc](https://docs.microsoft.com/en-us/azure/azure-app-configuration/quickstart-java-spring-app) for more details.

### Preparation

1. Create Azure App Configuration store

2. Create key value pairs in Azure App Configuration store
```xml
/application/azure.cosmosdb.database=[your-database]
/application/azure.cosmosdb.key=[your-cosmos-key]
/application/azure.cosmosdb.uri=[your-cosmos-uri]
```

3. Update bootstrap.properties file in this project, replace `{replace-with-your-store-connection-string}` with your App Configuration primary key.


### Run the application

Start the Spring Boot application `CosmosdbRefreshApplication`.

Access `localhost:8080`.

Check more APIs under the `UserController`.


