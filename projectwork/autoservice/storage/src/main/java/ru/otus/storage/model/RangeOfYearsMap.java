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
import static ru.otus.storage.model.YearsRange.EIGHTY_FIRST;
import static ru.otus.storage.model.YearsRange.EIGHTY_SIXTH;
import static ru.otus.storage.model.YearsRange.NINETY_FIRST;
import static ru.otus.storage.model.YearsRange.NINETY_SIXTH;
import static ru.otus.storage.model.YearsRange.SEVENTIES;
import static ru.otus.storage.model.YearsRange.SEVENTY_SIXTH;
import static ru.otus.storage.model.YearsRange.TWO_THOUSAND_ELEVEN;
import static ru.otus.storage.model.YearsRange.TWO_THOUSAND_FIRST;
import static ru.otus.storage.model.YearsRange.TWO_THOUSAND_SIXTEENTH;
import static ru.otus.storage.model.YearsRange.TWO_THOUSAND_SIXTH;

public class RangeOfYearsMap extends LinkedHashMap<String, YearsRange> {
    private static final RangeOfYearsMap ourInstance = new RangeOfYearsMap();

    public static RangeOfYearsMap getInstance() {
        return ourInstance;
    }

    private RangeOfYearsMap() {

        put("70", SEVENTIES);
        put("76", SEVENTY_SIXTH);
        put("81", EIGHTY_FIRST);
        put("86", EIGHTY_SIXTH);
        put("91", NINETY_FIRST);
        put("96", NINETY_SIXTH);
        put("01", TWO_THOUSAND_FIRST);
        put("06", TWO_THOUSAND_SIXTH);
        put("11", TWO_THOUSAND_ELEVEN);
        put("16", TWO_THOUSAND_SIXTEENTH);
    }
}
