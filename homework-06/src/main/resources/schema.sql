DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;

CREATE TABLE authors(
    id BIGINT PRIMARY KEY,
    fullname VARCHAR(255)
);

CREATE TABLE genres(
    id BIGINT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE books (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    author_id BIGINT,
    genre_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
);

CREATE TABLE comments (
    id BIGINT PRIMARY KEY,
    content VARCHAR(255),
    book_id BIGINT,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
)