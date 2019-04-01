package com.example.cosmosdbrefresh;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document(collection = "mycollection")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String email;

    private String name;

    @Override
    public String toString() {
        return String.format("%s: %s %s", this.id, this.email, this.name);
    }
}
