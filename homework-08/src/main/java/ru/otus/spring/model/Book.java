package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Document(collection = "books")
public class Book {
    @Id
    private long id;

    @Field(name = "title")
    private String title;

    @Field(name = "author_id")
    private Author author;

    @Field(name = "genre_id")
    private Genre genre;

}
