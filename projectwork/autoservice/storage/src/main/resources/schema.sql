drop table if exists storage.inventory;
drop table if exists storage.place;
drop table if exists storage.mark;
drop table if exists storage.model;
drop table if exists storage.range_of_years;
drop table if exists storage.part_type;
drop table if exists storage.part;
drop schema if exists storage;

create schema storage;

create table storage.place(
    id bigserial,
    name varchar(255) not null unique,
    primary key (id)
);

create table storage.inventory(
    id bigserial,
    count numeric not null,
    part_id bigserial,
    place_id bigserial,
    primary key (id)
);

create table storage.mark(
    id bigserial,
    name varchar(255) not null unique,
    primary key (id)
);

create table storage.model(
    id bigserial,
    name varchar(255) not null unique,
    primary key (id)
);

create table storage.range_of_years(
    id bigserial,
    article_range varchar(255) not null unique,
    primary key (id)
);

create table storage.part_type(
    id bigserial,
    name varchar(255) not null unique,
    article_part_type varchar(255) not null unique,
    primary key (id)
);

create table storage.part(
    id bigserial,
    article varchar(255) not null,
    part_type_id bigserial not null,
    mark_id bigserial not null,
    model_id bigserial not null,
    range_of_years_id bigserial not null,
    primary key (id)
);