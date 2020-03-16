package ru.otus.spring.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Survey {
    @CsvBindByName(column = "Вопросы")
    private String question;
    @CsvBindByName(column = "Ответы")
    private String answer;
}
