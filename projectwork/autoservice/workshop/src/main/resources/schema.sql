drop table if exists workshop.mechanic;
drop schema if exists workshop;

create schema workshop;

create table workshop.mechanic(
    id bigserial,
    primary key (id)
);
