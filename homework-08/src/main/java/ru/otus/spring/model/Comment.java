package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Data
@ToString(exclude = "book")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Document(collection = "comments")
public class Comment {
    @Id
    private long id;

    @Field(name = "content")
    private String content;

    @Field(name = "book_id")
    private Book book;

}
