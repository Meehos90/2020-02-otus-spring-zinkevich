insert into storage.place (name) values ('UNLOADING_ZONE');
insert into storage.place (name) values ('1A');
insert into storage.place (name) values ('1B');
insert into storage.place (name) values ('1C');
insert into storage.place (name) values ('1D');
insert into storage.place (name) values ('1E');

insert into storage.mark (name) values ('FORD');
insert into storage.mark (name) values ('HYUNDAI');
insert into storage.mark (name) values ('VOLKSWAGEN');
insert into storage.mark (name) values ('TOYOTA');

insert into storage.model (name) values ('MONDEO');
insert into storage.model (name) values ('MUSTANG');
insert into storage.model (name) values ('FOCUS');
insert into storage.model (name) values ('KUGA');
insert into storage.model (name) values ('FUSION');
insert into storage.model (name) values ('FIESTA');
insert into storage.model (name) values ('EXPLORER');

insert into storage.model (name) values ('SOLARIS');
insert into storage.model (name) values ('ELANTRA');
insert into storage.model (name) values ('SONATA');
insert into storage.model (name) values ('TUCSON');

insert into storage.model (name) values ('BEETLE');
insert into storage.model (name) values ('GOLF');
insert into storage.model (name) values ('POLO');
insert into storage.model (name) values ('TIGUAN');
insert into storage.model (name) values ('TOUAREG');
insert into storage.model (name) values ('TRANSPORTER');

insert into storage.model (name) values ('COROLLA');
insert into storage.model (name) values ('CAMRY');
insert into storage.model (name) values ('RAV4');
insert into storage.model (name) values ('LAND_CRUISER');
insert into storage.model (name) values ('HILUX');

insert into storage.range_of_years (article_range) values ('70');
insert into storage.range_of_years (article_range) values ('76');
insert into storage.range_of_years (article_range) values ('80');
insert into storage.range_of_years (article_range) values ('86');
insert into storage.range_of_years (article_range) values ('90');
insert into storage.range_of_years (article_range) values ('96');
insert into storage.range_of_years (article_range) values ('00');
insert into storage.range_of_years (article_range) values ('06');
insert into storage.range_of_years (article_range) values ('10');
insert into storage.range_of_years (article_range) values ('16');

insert into storage.part_type (name, article_part_type) values ('стекло лобовое', '00');
insert into storage.part_type (name, article_part_type) values ('стекло заднее', '01');
insert into storage.part_type (name, article_part_type) values ('стекло переднее правое', '02');
insert into storage.part_type (name, article_part_type) values ('стекло переднее левое', '03');
insert into storage.part_type (name, article_part_type) values ('стекло заднее правое', '04');
insert into storage.part_type (name, article_part_type) values ('стекло заднее левое', '05');

insert into storage.part_type (name, article_part_type) values ('колесо в сборе', '10');
insert into storage.part_type (name, article_part_type) values ('колесный диск', '11');
insert into storage.part_type (name, article_part_type) values ('шина', '12');

insert into storage.part_type (name, article_part_type) values ('капот', '20');
insert into storage.part_type (name, article_part_type) values ('багажник', '21');
insert into storage.part_type (name, article_part_type) values ('передняя левая дверь', '22');
insert into storage.part_type (name, article_part_type) values ('передняя правая дверь', '23');
insert into storage.part_type (name, article_part_type) values ('задняя левая дверь', '24');
insert into storage.part_type (name, article_part_type) values ('задняя правая дверь', '25');

insert into storage.part_type (name, article_part_type) values ('масляный фильтр', '30');
insert into storage.part_type (name, article_part_type) values ('воздушный фильтр', '31');
insert into storage.part_type (name, article_part_type) values ('топливный фильтр', '32');

insert into storage.part_type (name, article_part_type) values ('левая противотуманная фара', '40');
insert into storage.part_type (name, article_part_type) values ('правая противотуманная фара', '41');

insert into storage.part (article, part_type_id, mark_id, model_id, range_of_years_id) values ('FMO9600', 1, 1, 1, 6);
insert into storage.part (article, part_type_id, mark_id, model_id, range_of_years_id) values ('HSS1600', 1, 2, 8, 10);
insert into storage.part (article, part_type_id, mark_id, model_id, range_of_years_id) values ('HSS1610', 7, 2, 8, 10);

insert into storage.inventory (count, part_id, place_id) values (1, 1, 1);
insert into storage.inventory (count, part_id, place_id) values (1, 2, 2);
insert into storage.inventory (count, part_id, place_id) values  (8, 3, 3);