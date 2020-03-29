DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRES;
CREATE TABLE BOOKS(ID BIGINT PRIMARY KEY, TITLE VARCHAR(255), AUTHOR VARCHAR(255), GENRE VARCHAR(255));
CREATE TABLE AUTHORS(ID BIGINT PRIMARY KEY, FULLNAME VARCHAR(255));
CREATE TABLE GENRES(ID BIGINT PRIMARY KEY, NAME VARCHAR(255));