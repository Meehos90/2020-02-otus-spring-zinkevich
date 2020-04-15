insert into authors (id, fullname) values (nextval('author_id_seq'), 'Говард Филлипс Лавкрафт');
insert into authors (id, fullname) values (nextval('author_id_seq'), 'Джон Рональд Руэл Толкин');
insert into authors (id, fullname) values (nextval('author_id_seq'), 'Виктор Эмиль Франкл');

insert into genres (id, name) values (nextval('genre_id_seq'), 'Ужасы');
insert into genres (id, name) values (nextval('genre_id_seq'), 'Фэнтези');
insert into genres (id, name) values (nextval('genre_id_seq'), 'Психотерапия');

insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Хребты безумия', 1, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Властелин колец', 2, 2);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Человек в поисках смысла', 3, 3);

