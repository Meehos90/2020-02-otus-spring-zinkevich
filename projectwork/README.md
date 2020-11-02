# autoservice

#### В планах:
* добавить еще один микросервис, в котором будут находиться механики, и их можно будет добавлять в заказ-наряд.
* расширить модель Order (добавить туда механика)
* при создании Order'a убрать костыль текущее время + 2ч : order.setJobTime(LocalDateTime.now().plusHours(2));
* добавить spring-security в service-gateway
* доработать Hystrix-Dashboard

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

#### Как добавить з/ч на склад, и распределить их по местам:
1. [Доставить запчасти на склад](http://localhost:8080/autoservice/storage/inventory/actions/add-parts)  
```json 
{   
  "FMO9600": 1,   
  "HSS1600": 1,   
  "HSS1610": 4   
}    
```
2. [Убедиться, что артикул был декодирован и запчасть помещена на склад](http://localhost:8080/autoservice/storage/inventory/all-inventories)   

3. [Проверить что з/ч не отображаются в зоне выгрузки](http://localhost:8080/autoservice/storage/inventory/action/check-inventories-on-storage)
```json 
{   
  "FMO9600": 1,   
  "HSS1600": 1,   
  "HSS1610": 4   
}    
``` 

4. Сменить места запчастей, т.к. они находятся в зоне выгрузки, сервис-менеджеры ее не увидят
* [Сменить место лобового стекла на Ford Mondeo](http://localhost:8080/autoservice/storage/inventory/actions/change-place/1/1/2/1)    
* [Сменить место лобового стекла на Hyundai Solaris](http://localhost:8080/autoservice/storage/inventory/actions/change-place/1/2/3/1)    
* [Сменить место колес в сборе на Hyundai Solaris](http://localhost:8080/autoservice/storage/inventory/actions/change-place/1/3/4/2)    
* [Сменить место колес в сборе на Hyundai Solaris](http://localhost:8080/autoservice/storage/inventory/actions/change-place/1/3/5/2)    

5. [Проверить что теперь з/ч отображаются на складе](http://localhost:8080/autoservice/storage/inventory/action/check-inventories-on-storage)
```json 
{   
  "FMO9600": 1,   
  "HSS1600": 1,   
  "HSS1610": 4   
}    
```


#### Как создать заказ-наряд:
1. [Чтобы сервис-менеджер смог создать заказ-наряд, ему нужно узнать артикул з/ч для автомобиля (стекло лобовое на Hyundai Solaris 2019г.)](http://localhost:8080/autoservice/diagnostic/order/find-article-on-storage/%D1%81%D1%82%D0%B5%D0%BA%D0%BB%D0%BE%20%D0%BB%D0%BE%D0%B1%D0%BE%D0%B2%D0%BE%D0%B5/hyundai/solaris/2019)   
```json
HSS1600   
```
2. [Чтобы сервис-менеджер смог создать заказ-наряд, ему нужно узнать артикул з/ч для автомобиля (колесо в сборе на Hyundai Solaris 2019г.)](http://localhost:8080/autoservice/diagnostic/order/find-article-on-storage/%D0%BA%D0%BE%D0%BB%D0%B5%D1%81%D0%BE%20%D0%B2%20%D1%81%D0%B1%D0%BE%D1%80%D0%B5/hyundai/solaris/2019)   
```json
HSS1610   
```
3. [Также надо убедиться, что есть необходимое количество на складе](http://localhost:8080/autoservice/diagnostic/order/check-inventories-on-storage)   
```json
{   
  "HSS1600": 1,   
  "HSS1610": 4   
}   
```
4. [Создать заказ-наряд](http://localhost:8080/autoservice/diagnostic/order/add-order)   
```json
{   
   "afterWhatTime":2,   
   "partsAndCount":{   
      "HSS1600":1,   
      "HSS1610":4   
   }   
}
```
#### Выдача запчастей по заказ-наряду:

1. [Проверяем заказ-наряды, которые создал сервис-менеджер](http://localhost:8080/autoservice/storage/order/find-all-order-details)

2. Узнаем места, где они лежат
* [Место, где лежит лобовое стекло на Hyundai Solaris](http://localhost:8080/autoservice/storage/places/get-by-article/HSS1600)  
```json
[   
  {   
    "id": 3,   
    "name": "1B"   
  }   
]
```
* [Места, где лежат колеса на Hyundai Solaris](http://localhost:8080/autoservice/storage/places/get-by-article/HSS1610)  
```json
[   
  {   
    "id": 4,   
    "name": "1C"   
  },   
  {   
    "id": 5,   
    "name": "1D"   
  }   
]   
```
3. Выдаем з/ч со склада
* [Выдаем лобовое стекло на Hyundai Solaris](http://localhost:8080/autoservice/storage/parts/delete-part-from-storage/HSS1600)
* [Выдаем колеса на Hyundai Solaris](http://localhost:8080/autoservice/storage/parts/delete-part-from-storage/HSS1610)

} 

