insert into authors (id, fullname) values (nextval('author_id_seq'), 'Говард Лавкрафт');
insert into authors (id, fullname) values (nextval('author_id_seq'), 'Джон Толкин');
insert into authors (id, fullname) values (nextval('author_id_seq'), 'Виктор Франкл');
insert into authors (id, fullname) values (nextval('author_id_seq'), 'Стивен Кинг');
insert into authors (id, fullname) values (nextval('author_id_seq'), 'Эдгар Аллан По');

insert into genres (id, name) values (nextval('genre_id_seq'), 'ужасы');
insert into genres (id, name) values (nextval('genre_id_seq'), 'фэнтези');
insert into genres (id, name) values (nextval('genre_id_seq'), 'психотерапия');

insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Хребты Безумия', 1, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Зов Ктулху', 1, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Властелин колец', 2, 2);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Братство кольца', 2, 2);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Человек в поисках смысла', 3, 3);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Воля к смыслу', 3, 3);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Оно', 4, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Кладбище домашних животных', 4, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Падение дома Ашеров', 5, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Маска красной смерти', 5, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'Черный кот', 5, 1);

insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Страшная книга', 1);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Интересная книга', 1);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Очень понравилось', 2);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Не интересно читать', 2);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Крутая книжка, автор молодец', 3);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Не осилил', 3);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Скучно', 4);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Можно почитать', 4);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Самое увлекательное чтиво в жизни', 5);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Читаю в метро, пока интересно', 5);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'В жизни не читал ничего лучше', 6);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'Какая-то философия', 6);