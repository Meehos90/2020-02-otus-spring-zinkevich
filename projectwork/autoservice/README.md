# autoservice

В планах:
* добавить еще один микросервис, в котором будут находиться механики, и их можно будет добавлять в заказ-наряд.
* расширить модель Order (добавить туда механика)
* при создании Order'a убрать костыль текущее время + 2ч : order.setJobTime(LocalDateTime.now().plusHours(2));
* добавить spring-security в service-gateway
* запустить все через docker-compose 
* Доработать Hystrix-Dashboard

Технологичексий стек:
* spring-cloud-config
* spring-cloud-eureka
* spring-cloud-hystrix
* spring-cloud-zuul
* spring-cloud-feign
* docker
* spring-boot-actuator
* data-jpa
* swagger
* lombok
* DB postgres

Как создать заказ-наряд:
* [Доставить запчасти на склад](http://localhost:8080/autoservice/storage/iventory/actions/add-parts)
{
  "FMO9600": 5
}

* [Убедиться, что артикул был декодирован и запчасть помещена на склад](http://localhost:8080/autoservice/storage/inventory/all-inventories)


* [Сменить место запчасти, т.к. она находится в зоне выгрузки, мастера ее не увидят](http://localhost:8080/autoservice/storage/iventory/actions/change-place)
{
  "count": 5,
  "newPlaceId": 2,
  "partId": 1,
  "placeId": 1
}

* [Чтобы мастер смог создать заказ-наряд, ему нужно убедиться, что нужная з/ч есть на складе](http://localhost:8080/autoservice/diagnostic/order/find-article-on-storage/%D0%A1%D1%82%D0%B5%D0%BA%D0%BB%D0%BE%20%D0%9B%D0%BE%D0%B1%D0%BE%D0%B2%D0%BE%D0%B5/ford/mondeo/1998)

Стекло лобовое
ford
mondeo
1998

* [Создать заказ-наряд](http://localhost:8080/autoservice/diagnostic/order/add-order/FMO9600/1)

{
  "id": 1,
  "partsAndCount": "{\"FMO9600\":1}",
  "jobTime": "2020-08-17T20:05:34.781"
}
