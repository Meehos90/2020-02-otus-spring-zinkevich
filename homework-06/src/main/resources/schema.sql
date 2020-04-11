drop table if exists comments;
drop table if exists books;
drop table if exists authors;
drop table if exists genres;

drop sequence if exists author_id_seq;
create sequence author_id_seq;

drop sequence if exists genre_id_seq;
create sequence genre_id_seq;

drop sequence if exists book_id_seq;
create sequence book_id_seq;

drop sequence if exists comment_id_seq;
create sequence comment_id_seq;

create table authors(
    id bigint not null default nextval('author_id_seq'),
    fullname varchar(255) not null,
    primary key (id)
);

create table genres(
    id bigint default nextval('genre_id_seq'),
    name varchar(255),
    primary key (id)
);

create table books (
    id bigint default nextval('book_id_seq'),
    title varchar(255),
    author_id bigint,
    genre_id bigint,
    comment_id bigint,
    foreign key (author_id) references authors(id) on delete cascade,
    foreign key (genre_id) references genres(id) on delete cascade,
    primary key (id)
);

create table comments (
    id bigint default nextval('comment_id_seq'),
    content varchar(255),
    book_id bigint,
    foreign key (book_id) references books(id) on delete cascade,
    primary key (id)
)