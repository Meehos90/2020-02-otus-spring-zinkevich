package ru.otus.spring.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;

import javax.persistence.EntityManagerFactory;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory emf;
    private final MongoTemplate template;

    private static final int CHUNK_SIZE = 5;
    public static final String OUTPUT_DB_NAME = "outputDBName";
    public static final String INPUT_DB_NAME = "inputDBName";
    public static final String IMPORT_LIBRARY_JOB_NAME = "importLibraryJob";

    public static final String SELECT_BOOKS = "select b from Book b join Author a on b.author = a.id join Genre g on b.genre = g.id";
    public static final String SELECT_GENRES = "select g from Genre g";
    public static final String SELECT_COMMENTS = "select c from Comment c join Book b on c.book = b.id";
    public static final String SELECT_AUTHORS = "select a from Author a";

    @StepScope
    @Bean
    public JpaPagingItemReader<Author> authorReader() {
        return new JpaPagingItemReaderBuilder<Author>()
                .name("authorReader")
                .entityManagerFactory(emf)
                .queryString(SELECT_AUTHORS)
                .build();
    }

    @StepScope
    @Bean
    public MongoItemWriter<Author> authorWriter() {
        return new MongoItemWriterBuilder<Author>()
                .template(template)
                .collection("authors")
                .build();
    }

    @Bean
    public Step authorMigration() {
        return stepBuilderFactory.get("authorMigration")
                .<Author, Author>chunk(CHUNK_SIZE)
                .reader(authorReader())
                .writer(authorWriter())
                .build();
    }

    @StepScope
    @Bean
    public JpaPagingItemReader<Genre> genreReader() {
        return new JpaPagingItemReaderBuilder<Genre>()
                .name("genreReader")
                .entityManagerFactory(emf)
                .queryString(SELECT_GENRES)
                .build();
    }

    @StepScope
    @Bean
    public MongoItemWriter<Genre> genreWriter() {
        return new MongoItemWriterBuilder<Genre>()
                .template(template)
                .collection("genres")
                .build();
    }

    @Bean
    public Step genreMigration() {
        return stepBuilderFactory.get("genreMigration")
                .<Genre, Genre>chunk(CHUNK_SIZE)
                .reader(genreReader())
                .writer(genreWriter())
                .build();
    }

    @StepScope
    @Bean
    public JpaPagingItemReader<Book> bookReader() {
        return new JpaPagingItemReaderBuilder<Book>()
                .name("bookReader")
                .entityManagerFactory(emf)
                .queryString(SELECT_BOOKS)
                .build();
    }

    @StepScope
    @Bean
    public MongoItemWriter<Book> bookWriter() {
        return new MongoItemWriterBuilder<Book>()
                .template(template)
                .collection("books")
                .build();
    }

    @Bean
    public Step bookMigration() {
        return stepBuilderFactory.get("bookMigration")
                .<Book, Book>chunk(CHUNK_SIZE)
                .reader(bookReader())
                .writer(bookWriter())
                .build();
    }

    @StepScope
    @Bean
    public JpaPagingItemReader<Comment> commentReader() {
        return new JpaPagingItemReaderBuilder<Comment>()
                .name("commentReader")
                .entityManagerFactory(emf)
                .queryString(SELECT_COMMENTS)
                .build();
    }

    @StepScope
    @Bean
    public MongoItemWriter<Comment> commentWriter() {
        return new MongoItemWriterBuilder<Comment>()
                .template(template)
                .collection("comments")
                .build();
    }

    @Bean
    public Step commentMigration() {
        return stepBuilderFactory.get("commentMigration")
                .<Comment, Comment>chunk(CHUNK_SIZE)
                .reader(commentReader())
                .writer(commentWriter())
                .build();
    }

    @Bean
    public Job migrateLibraryJob() {
        return jobBuilderFactory.get(IMPORT_LIBRARY_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(authorMigration())
                .next(genreMigration())
                .next(bookMigration())
                .next(commentMigration())
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Начало job");
                    }
                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }

}
