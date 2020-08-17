package ru.otus.storage.model;

import java.util.LinkedHashMap;

import static ru.otus.storage.model.YearsRange.*;

public class RangeOfYearsMap extends LinkedHashMap<String, YearsRange> {
    private static final RangeOfYearsMap ourInstance = new RangeOfYearsMap();

    public static RangeOfYearsMap getInstance() {
        return ourInstance;
    }

    private RangeOfYearsMap() {

        put("70", SEVENTIES);
        put("76", SEVENTY_SIXTH);
        put("80", EIGHTIES);
        put("86", EIGHTY_SIXTH);
        put("90", NINETIETH);
        put("96", NINETY_SIXTH);
        put("00", TWO_THOUSANDTH);
        put("06", TWO_THOUSAND_SIXTH);
        put("10", TWO_THOUSAND_TENTH);
        put("16", TWO_THOUSAND_SIXTEENTH);
    }
}
