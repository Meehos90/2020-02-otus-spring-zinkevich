package ru.otus.spring.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "genre")
public class Genre {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    private String id;
    
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
