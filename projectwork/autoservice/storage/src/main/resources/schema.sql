drop table if exists storage.inventory;
drop table if exists storage.part;
drop table if exists storage.place;
drop schema if exists storage;

create schema storage;

create table storage.part(
    id bigserial,
    article varchar(255) not null,
    name varchar(255) not null,
    auto_mark varchar(255) not null,
    auto_model varchar(255) not null,
    range_of_years varchar(255) not null,
    primary key (id)
);

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