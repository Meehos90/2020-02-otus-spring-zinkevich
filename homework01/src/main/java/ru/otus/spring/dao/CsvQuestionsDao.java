package ru.otus.spring.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.model.Interrogation;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

@AllArgsConstructor
public class CsvQuestionsDao implements QuestionsDao {
    private final String csvFile;

    @Override
    public List<Interrogation> csvFileRead() {
        InputStreamReader isReader = new InputStreamReader(
                Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(csvFile)), UTF_8);
        CsvToBean csvToBean = new CsvToBeanBuilder(isReader)
                .withType(Interrogation.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return (List<Interrogation>) csvToBean.parse();
    }

}
