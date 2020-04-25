package ru.otus.spring.events.book;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.book.BookRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveAuthorEventsListener extends AbstractMongoEventListener<Author> {
    
    private final BookRepository bookRepository;
    
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Author> event) {
        super.onBeforeConvert(event);
        val author = event.getSource();
        val id = author.getId();
        List<Book> books = bookRepository.findByAuthorId(id);
        books.forEach(book -> {
            book.setAuthor(author);
            bookRepository.save(book);
        });
    }
}
