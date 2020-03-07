package ru.otus.spring.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.exception.SurveysLoadingException;
import ru.otus.spring.model.Survey;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

@Repository
@RequiredArgsConstructor
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
            throw new SurveysLoadingException(e.getMessage());
        }
    }

}
