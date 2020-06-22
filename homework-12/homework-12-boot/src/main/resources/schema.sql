drop table if exists comments;
drop table if exists books;
drop table if exists authors;
drop table if exists genres;
drop table if exists user_roles;
drop table if exists roles;
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

create table roles (
    id bigserial,
    name varchar(60) not null unique,
    primary key (id)
);

create table users (
    id bigserial,
    username varchar(255) not null unique,
    password varchar(255) not null,
    primary key (id)
);

create table user_roles (
    user_id bigserial not null,
    role_id bigserial not null,
    foreign key (user_id) references users(id) on update cascade,
    foreign key (role_id) references roles(id) on update cascade,
    constraint user_roles_pkey primary key (user_id, role_id)
);

