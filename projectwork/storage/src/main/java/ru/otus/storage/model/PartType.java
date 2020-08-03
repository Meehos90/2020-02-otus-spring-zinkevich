package ru.otus.storage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartType {
    WINDSHIELD("Стекло лобовое"),
    REAR_GLASS("Стекло заднее"),
    FRONT_RIGHT_GLASS("Стекло переднее правое"),
    FRONT_LEFT_GLASS("Стекло переднее левое"),
    REAR_RIGHT_GLASS("Стекло заднее правое"),
    REAR_LEFT_GLASS("Стекло заднее левое"),

    WHEEL("Колесо в сборе"),
    RIM("Колесный диск"),
    TIRE("Шина"),

    HOOD("Капот"),
    TRUNK("Багажник"),
    FRONT_LEFT_DOOR("Передняя левая дверь"),
    FRONT_RIGHT_DOOR("Передняя правая дверь"),
    REAR_LEFT_DOOR("Задняя левая дверь"),
    REAR_RIGHT_DOOR("Задняя правая дверь"),

    OIL_FILTER("Масляный фильтр"),
    AIR_FILTER("Воздушный фильтр"),
    FUEL_FILTER("Топливный фильтр"),

    LEFT_FOG_LAMP("Левая противотуманная фара"),
    RIGHT_FOG_LAMP("Правая противотуманная фара");


    private String name;
}
