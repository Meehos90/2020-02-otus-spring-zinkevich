insert into authors (id, fullname) values (nextval('author_id_seq'), 'говард лавкрафт');
insert into authors (id, fullname) values (nextval('author_id_seq'), 'джон толкин');
insert into authors (id, fullname) values (nextval('author_id_seq'), 'виктор франкл');

insert into genres (id, name) values (nextval('genre_id_seq'), 'ужасы');
insert into genres (id, name) values (nextval('genre_id_seq'), 'фэнтези');
insert into genres (id, name) values (nextval('genre_id_seq'), 'психотерапия');

insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'хребты безумия', 1, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'зов ктулху', 1, 1);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'властелин колец', 2, 2);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'братство кольца', 2, 2);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'человек в поисках смысла', 3, 3);
insert into books (id, title, author_id, genre_id) values (nextval('book_id_seq'), 'воля к смыслу', 3, 3);

insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'страшная книга', 1);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'интересная книга', 1);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'очень понравилось', 2);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'не интересно читать', 2);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'крутая книжка, автор молодец', 3);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'не осилил', 3);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'скучно', 4);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'можно почитать', 4);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'самое увлекательное чтиво в жизни', 5);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'читаю в метро, пока интересно', 5);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'в жизни не читал ничего лучше', 6);
insert into comments (id, content, book_id) values (nextval('comment_id_seq'), 'какая-то философия', 6);