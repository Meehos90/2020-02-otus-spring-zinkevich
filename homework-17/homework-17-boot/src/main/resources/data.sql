insert into authors (fullname) values ('Говард Лавкрафт');
insert into authors (fullname) values ('Джон Толкин');
insert into authors (fullname) values ('Виктор Франкл');
insert into authors (fullname) values ('Стивен Кинг');
insert into authors (fullname) values ('Эдгар Аллан По');

insert into genres (name) values ('ужасы');
insert into genres (name) values ('фэнтези');
insert into genres (name) values ('психотерапия');

insert into books (title, author_id, genre_id) values ('Хребты Безумия', 1, 1);
insert into books (title, author_id, genre_id) values ('Зов Ктулху', 1, 1);
insert into books (title, author_id, genre_id) values ('Властелин колец', 2, 2);
insert into books (title, author_id, genre_id) values ('Братство кольца', 2, 2);
insert into books (title, author_id, genre_id) values ('Человек в поисках смысла', 3, 3);
insert into books (title, author_id, genre_id) values ('Воля к смыслу', 3, 3);
insert into books (title, author_id, genre_id) values ('Оно', 4, 1);
insert into books (title, author_id, genre_id) values ('Кладбище домашних животных', 4, 1);
insert into books (title, author_id, genre_id) values ('Падение дома Ашеров', 5, 1);
insert into books (title, author_id, genre_id) values ('Маска красной смерти', 5, 1);
insert into books (title, author_id, genre_id) values ('Черный кот', 5, 1);

insert into comments (content, book_id) values ('Страшная книга', 1);
insert into comments (content, book_id) values ('Интересная книга', 1);
insert into comments (content, book_id) values ('Очень понравилось', 2);
insert into comments (content, book_id) values ('Не интересно читать', 2);
insert into comments (content, book_id) values ('Крутая книжка, автор молодец', 3);
insert into comments (content, book_id) values ('Не осилил', 3);
insert into comments (content, book_id) values ('Скучно', 4);
insert into comments (content, book_id) values ('Можно почитать', 4);
insert into comments (content, book_id) values ('Самое увлекательное чтиво в жизни', 5);
insert into comments (content, book_id) values ('Читаю в метро, пока интересно', 5);
insert into comments (content, book_id) values ('В жизни не читал ничего лучше', 6);
insert into comments (content, book_id) values ('Какая-то философия', 6);