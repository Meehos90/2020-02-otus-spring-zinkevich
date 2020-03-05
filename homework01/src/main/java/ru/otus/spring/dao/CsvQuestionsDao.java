package ru.otus.spring.dao;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.AllArgsConstructor;
import ru.otus.spring.exception.QuestionsLoadingException;
import ru.otus.spring.model.Survey;

@AllArgsConstructor
public class CsvQuestionsDao implements QuestionsDao {
    private final String csvFile;

    @Override
    public List<Survey> csvFileRead() {
        try {
        InputStreamReader isReader = new InputStreamReader(
                Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(csvFile)), UTF_8);
        CsvToBean csvToBean =
                new CsvToBeanBuilder(isReader).withType(Survey.class).withIgnoreLeadingWhiteSpace(true).build();

            return csvToBean.parse();

        } catch (Exception e) {
            throw new QuestionsLoadingException(e.getMessage());
        }

    }

}
