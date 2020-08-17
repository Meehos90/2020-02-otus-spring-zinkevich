package ru.otus.storage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YearsRange {
    SEVENTIES("с 1970 по 1975"),
    SEVENTY_SIXTH("с 1976 по 1979"),
    EIGHTIES("с 1980 по 1985"),
    EIGHTY_SIXTH("с 1986 по 1989"),
    NINETIETH("с 1990 по 1995"),
    NINETY_SIXTH("с 1996 по 1999"),
    TWO_THOUSANDTH("с 2000 по 2005"),
    TWO_THOUSAND_SIXTH("с 2006 по 2009"),
    TWO_THOUSAND_TENTH("с 2010 по 2015"),
    TWO_THOUSAND_SIXTEENTH("с 2016 по 2020");

    private String range;
}
