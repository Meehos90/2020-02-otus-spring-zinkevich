package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "author")
public class Author {
    @Id
    private String id;

    @NotEmpty
    private String fullName;

    public Author(String fullName) {
        this.fullName = fullName;
    }
}
