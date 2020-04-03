package ru.otus.spring.dao.book;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookRelation {
    private final long authorId;
    private final long genreId;
}
