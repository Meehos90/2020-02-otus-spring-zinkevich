package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static ru.otus.spring.service.impl.localization.LocalizationServiceImpl.language;

@Service
public class LocaleQuestionsDao {
    private final QuestionsDao enDao;
    private final QuestionsDao ruDao;

    public LocaleQuestionsDao(@Qualifier("questionsDao") QuestionsDao enDao, @Qualifier("questionsDaoRu") QuestionsDao ruDao) {
        this.enDao = enDao;
        this.ruDao = ruDao;
    }

    public QuestionsDao chooseQuestionsDao() {
        if(language.equals("english")) {
            return enDao;
        }
        else if(language.equals("russian")){
            return ruDao;
        }
        return enDao;
    }
}
