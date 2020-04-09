insert into authors (id, fullname) values (1, 'Говард Лавкрафт');
insert into authors (id, fullname) values (2, 'Джон Толкин');
insert into authors (id, fullname) values (3, 'Виктор Франкл');

insert into genres (id, name) values (1, 'Ужасы');
insert into genres (id, name) values (2, 'Фэнтези');
insert into genres (id, name) values (3, 'Психотерапия');

insert into books (id, title, author_id, genre_id) values (1, 'Хребты безумия', 1, 1);
insert into books (id, title, author_id, genre_id) values (2, 'Зов Ктулху', 1, 1);
insert into books (id, title, author_id, genre_id) values (3, 'Властелин колец', 2, 2);
insert into books (id, title, author_id, genre_id) values (4, 'Братство кольца', 2, 2);
insert into books (id, title, author_id, genre_id) values (5, 'Человек в поисках смысла', 3, 3);
insert into books (id, title, author_id, genre_id) values (6, 'Воля к смыслу', 3, 3);

insert into comments (id, content, book_id) values (1, 'Страшная книга', 1);
insert into comments (id, content, book_id) values (2, 'Интересная книга', 3);
insert into comments (id, content, book_id) values (3, 'Не осилил', 5);