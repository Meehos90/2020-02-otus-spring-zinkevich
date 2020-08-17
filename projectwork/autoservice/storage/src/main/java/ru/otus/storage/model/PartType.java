package ru.otus.storage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartType {
    WINDSHIELD("стекло лобовое"),
    REAR_GLASS("стекло заднее"),
    FRONT_RIGHT_GLASS("стекло переднее правое"),
    FRONT_LEFT_GLASS("стекло переднее левое"),
    REAR_RIGHT_GLASS("стекло заднее правое"),
    REAR_LEFT_GLASS("стекло заднее левое"),

    WHEEL("колесо в сборе"),
    RIM("колесный диск"),
    TIRE("шина"),

    HOOD("капот"),
    TRUNK("багажник"),
    FRONT_LEFT_DOOR("передняя левая дверь"),
    FRONT_RIGHT_DOOR("передняя правая дверь"),
    REAR_LEFT_DOOR("задняя левая дверь"),
    REAR_RIGHT_DOOR("задняя правая дверь"),

    OIL_FILTER("масляный фильтр"),
    AIR_FILTER("воздушный фильтр"),
    FUEL_FILTER("топливный фильтр"),

    LEFT_FOG_LAMP("левая противотуманная фара"),
    RIGHT_FOG_LAMP("правая противотуманная фара");


    private String name;
}
