package ru.otus.spring.dao;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import ru.otus.spring.exception.SurveysLoadingException;
import ru.otus.spring.logging.Logger;
import ru.otus.spring.model.Survey;

@Slf4j
@Repository
public class CsvQuestionsDao implements QuestionsDao {
    private String csvFile;

    public CsvQuestionsDao(@Value("${csv.file}") String csvFile) {
        this.csvFile = csvFile;
    }

    @Logger
    @Override
    public List<Survey> csvFileRead() {
        try {
            InputStreamReader isReader = new InputStreamReader(
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(csvFile)), UTF_8);
            CsvToBean csvToBean =
                    new CsvToBeanBuilder(isReader).withType(Survey.class).withIgnoreLeadingWhiteSpace(true).withSkipLines(1).build();
            return csvToBean.parse();
        } catch (Exception e) {
            throw new SurveysLoadingException(e.getMessage());
        }
    }
}
