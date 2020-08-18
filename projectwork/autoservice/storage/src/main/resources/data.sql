insert into storage.place (name) values ('UNLOADING_ZONE');
insert into storage.place (name) values ('1A');
insert into storage.place (name) values ('1B');
insert into storage.place (name) values ('1C');
insert into storage.place (name) values ('1D');
insert into storage.place (name) values ('1E');

insert into storage.part (article, name, auto_mark, auto_model, range_of_years) values ('FMO9600', 'стекло лобовое', 'FORD', 'MONDEO', 'с 1996 по 1999');
insert into storage.part (article, name, auto_mark, auto_model, range_of_years) values ('HSS1600', 'стекло лобовое', 'HYUNDAI', 'SOLARIS', 'с 2016 по 2020');
insert into storage.part (article, name, auto_mark, auto_model, range_of_years) values ('HSS1610', 'колесо в сборе', 'HYUNDAI', 'SOLARIS', 'с 2016 по 2020');

insert into storage.inventory (count, part_id, place_id) values (1, 1, 1);
insert into storage.inventory (count, part_id, place_id) values (1, 2, 2);
insert into storage.inventory (count, part_id, place_id) values  (8, 3, 3);