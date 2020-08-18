# autoservice

#### В планах:
* добавить еще один микросервис, в котором будут находиться механики, и их можно будет добавлять в заказ-наряд.
* расширить модель Order (добавить туда механика)
* при создании Order'a убрать костыль текущее время + 2ч : order.setJobTime(LocalDateTime.now().plusHours(2));
* добавить spring-security в service-gateway
* запустить все через docker-compose 
* доработать Hystrix-Dashboard
* добавить hystrix

#### Технологичексий стек:
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

##### Сейчас на складе есть стекло лобовое и колеса в сборе для HYUNDAI SOLARIS [добавлено для тестирования]

#### Как добавить запчасти на склад:
* [Доставить запчасти на склад](http://localhost:8080/autoservice/storage/iventory/actions/add-parts)   
{   
  "TCA1033": 20   
}   
* [Убедиться, что артикул был декодирован и запчасть помещена на склад](http://localhost:8080/autoservice/storage/inventory/all-inventories)   
* [Сменить место запчасти, т.к. она находится в зоне выгрузки, мастера ее не увидят](http://localhost:8080/autoservice/storage/iventory/actions/change-place)    
{   
  "count": 20,   
  "newPlaceId": 4,   
  "partId": 4,   
  "placeId": 1   
}   


#### Как создать заказ-наряд:
* [Чтобы мастер смог создать заказ-наряд, ему нужно найти артикул по данным авто](http://localhost:8080/autoservice/diagnostic/order/find-article-on-storage/%D1%81%D1%82%D0%B5%D0%BA%D0%BB%D0%BE%20%D0%BB%D0%BE%D0%B1%D0%BE%D0%B2%D0%BE%D0%B5/hyundai/solaris/2019)   
Стекло лобовое   
hyundai   
solaris   
2019   

HSS1600   

* [Чтобы мастер смог создать заказ-наряд, ему нужно найти артикул по данным авто](http://localhost:8080/autoservice/diagnostic/order/find-article-on-storage/%D0%BA%D0%BE%D0%BB%D0%B5%D1%81%D0%BE%20%D0%B2%20%D1%81%D0%B1%D0%BE%D1%80%D0%B5/hyundai/solaris/2019)   
Колесо в сборе     
hyundai   
solaris
2019

HSS1610   

* [Далее нужно проверить, есть ли данные з/ч в нужном кол-ве на складе](http://localhost:8080/autoservice/diagnostic/order/check-inventories-on-storage)   
{   
  "HSS1600": 1,   
  "HSS1610": 4   
}   

* [Создать заказ-наряд](http://localhost:8080/autoservice/diagnostic/order/add-order)   
{   
  "afterWhatTime": 2,   
  "partsAndCount": 
  {"HSS1600": 1, "HSS1610": 4}        
}   

