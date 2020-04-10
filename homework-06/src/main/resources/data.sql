insert into authors (id, fullname) values (nextval('author_id_seq'), 'Говард Лавкрафт');
insert into authors (id, fullname) values (nextval('author_id_seq'), 'Джон Толкин');
insert into authors (id, fullname) values (nextval('author_id_seq'), 'Виктор Франкл');

insert into genres (id, name) values (nextval('genre_id_seq'), 'Ужасы');
insert into genres (id, name) values (nextval('genre_id_seq'), 'Фэнтези');
insert into genres (id, name) values (nextval('genre_id_seq'), 'Психотерапия');

insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Хребты безумия', 1, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Зов Ктулху', 1, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Властелин колец', 2, 2);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Братство кольца', 2, 2);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Человек в поисках смысла', 3, 3);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Воля к смыслу', 3, 3);

insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Страшная книга', 1);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Интересная книга', 3);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Не осилил', 5);