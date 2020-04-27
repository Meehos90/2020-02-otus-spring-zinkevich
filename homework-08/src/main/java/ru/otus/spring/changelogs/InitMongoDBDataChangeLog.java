package ru.otus.spring.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {
    private Author howardLovecraft;
    private Author johnTolkien;
    private Author viktorFrankl;

    private Genre horror;
    private Genre fantasy;
    private Genre psychotherapy;

    private Book mountainsOfMadness;
    private Book callOfCthulhu;
    private Book lordOfTheRings;
    private Book fellowshipOfTheRing;
    private Book searchForMeaning;
    private Book willToMeaning;

    @ChangeSet(order = "000", id = "dropDB", author = "mnzinkev", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "mnzinkev", runAlways = true)
    public void initAuthors(MongoTemplate template) {
        howardLovecraft = template.save(new Author("говард лавкрафт"));
        johnTolkien = template.save(new Author("джон толкин"));
        viktorFrankl = template.save(new Author("виктор франкл"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "mnzinkev", runAlways = true)
    public void initGenres(MongoTemplate template) {
        horror = template.save(new Genre("ужасы"));
        fantasy = template.save(new Genre("фэнтези"));
        psychotherapy = template.save(new Genre("психотерапия"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "mnzinkev", runAlways = true)
    public void initBooks(MongoTemplate template) {
        mountainsOfMadness = template.save(new Book("хребты безумия", howardLovecraft, horror));
        callOfCthulhu = template.save(new Book("зов ктулху", howardLovecraft, horror));

        lordOfTheRings = template.save(new Book("властелин колец", johnTolkien, fantasy));
        fellowshipOfTheRing = template.save(new Book("братство кольца", johnTolkien, fantasy));

        searchForMeaning = template.save(new Book("человек в поисках смысла", viktorFrankl, psychotherapy));
        willToMeaning = template.save(new Book("воля к смыслу", viktorFrankl, psychotherapy));
    }

    @ChangeSet(order = "004", id = "initComments", author = "mnzinkev", runAlways = true)
    public void initComments(MongoTemplate template) {
        template.save(new Comment("страшная книга", mountainsOfMadness));
        template.save(new Comment("интересная книга", mountainsOfMadness));
        template.save(new Comment("очень понравилось", callOfCthulhu));
        template.save(new Comment("не интересно читать", callOfCthulhu));
        template.save(new Comment("крутая книжка, автор молодец", lordOfTheRings));
        template.save(new Comment("не осилил", lordOfTheRings));
        template.save(new Comment("скучно", fellowshipOfTheRing));
        template.save(new Comment("можно почитать", fellowshipOfTheRing));
        template.save(new Comment("самое увлекательное чтиво в жизни", searchForMeaning));
        template.save(new Comment("читаю в метро, пока интересно", searchForMeaning));
        template.save(new Comment("в жизни не читал ничего лучше", willToMeaning));
        template.save(new Comment("какая-то философия", willToMeaning));
    }
}
