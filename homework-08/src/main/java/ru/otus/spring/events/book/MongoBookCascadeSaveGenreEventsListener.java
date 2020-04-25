package ru.otus.spring.events.book;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveGenreEventsListener extends AbstractMongoEventListener<Genre> {
    
    private final BookRepository bookRepository;
    
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Genre> event) {
        super.onBeforeConvert(event);
        val genre = event.getSource();
        val id = genre.getId();
        List<Book> books = bookRepository.findByGenreId(id);
        books.forEach(book -> {
            book.setGenre(genre);
            bookRepository.save(book);
        });
    }
}
