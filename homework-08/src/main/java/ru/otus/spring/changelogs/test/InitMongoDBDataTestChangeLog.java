package ru.otus.spring.changelogs.test;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;

@ChangeLog(order = "001")
public class InitMongoDBDataTestChangeLog {
    private Author testAuthor;
    private Genre testGenre;
    private Book testBook;

    @ChangeSet(order = "000", id = "dropDB", author = "mnzinkev", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "mnzinkev", runAlways = true)
    public void initAuthors(MongoTemplate template) {
        testAuthor = template.save(new Author("стивен кинг"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "mnzinkev", runAlways = true)
    public void initGenres(MongoTemplate template) {
        testGenre = template.save(new Genre("ужасы"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "mnzinkev", runAlways = true)
    public void initBooks(MongoTemplate template) {
        testBook = template.save(new Book("оно", testAuthor, testGenre));
    }

    @ChangeSet(order = "004", id = "initComments", author = "mnzinkev", runAlways = true)
    public void initComments(MongoTemplate template) {
        template.save(new Comment("страшная книга", testBook));
    }
}
