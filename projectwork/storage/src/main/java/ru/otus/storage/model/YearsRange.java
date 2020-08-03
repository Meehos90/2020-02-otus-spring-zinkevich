package ru.otus.storage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YearsRange {
    SEVENTIES("с 1970 по 1975"),
    SEVENTY_SIXTH("с 1976 по 1980"),
    EIGHTY_FIRST("с 1981 по 1985"),
    EIGHTY_SIXTH("с 1986 по 1990"),
    NINETY_FIRST("с 1991 по 1995"),
    NINETY_SIXTH("с 1996 по 2000"),
    TWO_THOUSAND_FIRST("с 2001 по 2005"),
    TWO_THOUSAND_SIXTH("с 2006 по 2010"),
    TWO_THOUSAND_ELEVEN("с 2011 по 2015"),
    TWO_THOUSAND_SIXTEENTH("с 2016 по 2020");

    private String range;
}
