package ru.otus.spring.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.spring.config.LocalizationProperties;
import ru.otus.spring.exception.SurveysLoadingException;
import ru.otus.spring.logging.Logger;
import ru.otus.spring.model.Survey;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Repository
public class CsvQuestionsDao implements QuestionsDao {
    private final LocalizationProperties localProps;

    public CsvQuestionsDao(LocalizationProperties localProps) {
        this.localProps = localProps;
    }

    @Logger
    @Override
    public List<Survey> csvFileRead() {
        try {
            InputStreamReader isReader = new InputStreamReader(
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(localProps.getCsvFile())), UTF_8);
            CsvToBean csvToBean =
                    new CsvToBeanBuilder(isReader).withType(Survey.class).withIgnoreLeadingWhiteSpace(true).withSkipLines(1).build();
            return csvToBean.parse();
        } catch (Exception e) {
            throw new SurveysLoadingException(e.getMessage());
        }
    }
}
