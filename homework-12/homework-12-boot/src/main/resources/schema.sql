drop table if exists comments;
drop table if exists books;
drop table if exists authors;
drop table if exists genres;
drop table if exists users;

create table authors(
    id bigserial,
    fullname varchar(255) not null unique,
    primary key (id)
);

create table genres(
    id bigserial,
    name varchar(255) not null unique,
    primary key (id)
);

create table books (
    id bigserial,
    title varchar(255) not null unique,
    author_id bigserial,
    genre_id bigserial,
    comment_id bigserial,
    foreign key (author_id) references authors(id) on delete cascade,
    foreign key (genre_id) references genres(id) on delete cascade,
    primary key (id)
);

create table comments (
    id bigserial,
    content varchar(255) not null unique,
    book_id bigserial,
    foreign key (book_id) references books(id) on delete cascade,
    primary key (id)
);

create table users (
    id bigserial,
    name varchar(255) not null unique,
    password varchar(255) not null,
    roles varchar(255) not null,
    primary key (id)
)

