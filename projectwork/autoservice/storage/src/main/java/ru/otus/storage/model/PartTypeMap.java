package ru.otus.storage.model;

import java.util.LinkedHashMap;

import static ru.otus.storage.model.PartType.AIR_FILTER;
import static ru.otus.storage.model.PartType.FRONT_LEFT_DOOR;
import static ru.otus.storage.model.PartType.FRONT_LEFT_GLASS;
import static ru.otus.storage.model.PartType.FRONT_RIGHT_DOOR;
import static ru.otus.storage.model.PartType.FRONT_RIGHT_GLASS;
import static ru.otus.storage.model.PartType.FUEL_FILTER;
import static ru.otus.storage.model.PartType.HOOD;
import static ru.otus.storage.model.PartType.LEFT_FOG_LAMP;
import static ru.otus.storage.model.PartType.OIL_FILTER;
import static ru.otus.storage.model.PartType.REAR_GLASS;
import static ru.otus.storage.model.PartType.REAR_LEFT_DOOR;
import static ru.otus.storage.model.PartType.REAR_LEFT_GLASS;
import static ru.otus.storage.model.PartType.REAR_RIGHT_DOOR;
import static ru.otus.storage.model.PartType.REAR_RIGHT_GLASS;
import static ru.otus.storage.model.PartType.RIGHT_FOG_LAMP;
import static ru.otus.storage.model.PartType.RIM;
import static ru.otus.storage.model.PartType.TIRE;
import static ru.otus.storage.model.PartType.TRUNK;
import static ru.otus.storage.model.PartType.WHEEL;
import static ru.otus.storage.model.PartType.WINDSHIELD;


public class PartTypeMap extends LinkedHashMap<String, PartType> {
    private static final PartTypeMap ourInstance = new PartTypeMap();

    public static PartTypeMap getInstance() {
        return ourInstance;
    }

    private PartTypeMap() {

        put("00", WINDSHIELD);
        put("01", REAR_GLASS);
        put("02", FRONT_RIGHT_GLASS);
        put("03", FRONT_LEFT_GLASS);
        put("04", REAR_RIGHT_GLASS);
        put("05", REAR_LEFT_GLASS);

        put("10", WHEEL);
        put("11", RIM);
        put("12", TIRE);

        put("20", HOOD);
        put("21", TRUNK);
        put("22", FRONT_LEFT_DOOR);
        put("23", FRONT_RIGHT_DOOR);
        put("24", REAR_LEFT_DOOR);
        put("25", REAR_RIGHT_DOOR);

        put("30", OIL_FILTER);
        put("31", AIR_FILTER);
        put("33", FUEL_FILTER);

        put("40", LEFT_FOG_LAMP);
        put("41", RIGHT_FOG_LAMP);

    }
}
