insert into authors (id, fullname) values (1, 'Говард Филлипс Лавкрафт');
insert into authors (id, fullname) values (2, 'Джон Рональд Руэл Толкин');
insert into authors (id, fullname) values (3, 'Виктор Эмиль Франкл');

insert into genres (id, name) values (1, 'Ужасы');
insert into genres (id, name) values (2, 'Фэнтези');
insert into genres (id, name) values (3, 'Психотерапия');

insert into books (id, title, author_id, genre_id) values (1, 'Хребты безумия', 1, 1);
insert into books (id, title, author_id, genre_id) values (2, 'Властелин колец', 2, 2);
insert into books (id, title, author_id, genre_id) values (3, 'Человек в поисках смысла', 3, 3);

