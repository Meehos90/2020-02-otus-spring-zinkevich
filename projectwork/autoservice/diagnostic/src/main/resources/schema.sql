drop table if exists diagnostic.order;
drop schema if exists diagnostic;

create schema diagnostic;

create table diagnostic.order(
    id bigserial,
    parts_and_count varchar(255),
    job_time timestamp,
    primary key (id)
);