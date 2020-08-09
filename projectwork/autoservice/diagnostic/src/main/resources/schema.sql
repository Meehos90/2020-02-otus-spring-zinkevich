drop table if exists diagnostic.work_order;
drop table if exists diagnostic.automobile;
drop schema if exists diagnostic;

create schema diagnostic;

create table diagnostic.automobile(
    id bigserial,
    primary key (id)
);

create table diagnostic.work_order(
    id bigserial,
    primary key (id)
);