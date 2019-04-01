package com.example.cosmosdbrefresh;

import com.microsoft.azure.spring.data.cosmosdb.config.DocumentDBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired

    private UserRepository userRepository;

    @Autowired
    private DocumentDbProperties properties;

    @Autowired
    private DocumentDBConfig config;

    @GetMapping
    public String getUser() {
        return userRepository.findById("1").get().toString();
    }

    @GetMapping("/db/key")
    public String getDbKey() {
        return properties.getKey();
    }

    @GetMapping("/dbconfig")
    public String getDbCOnfig() {
        return config.getKey();
    }
}
