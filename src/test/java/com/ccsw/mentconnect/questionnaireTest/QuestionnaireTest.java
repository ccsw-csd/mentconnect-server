package com.ccsw.mentconnect.questionnaireTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.mentconnect.questionnaire.logic.QuestionnaireServiceImpl;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireRepository;

@ExtendWith(MockitoExtension.class)
public class QuestionnaireTest {

    @Mock
    private QuestionnaireRepository questionnaireRepository;

    @InjectMocks
    private QuestionnaireServiceImpl questionnaireService;

    @Test
    public void findAllShouldReturnAllQuestionnaires() {

        List<QuestionnaireEntity> list = new ArrayList<>();

        list.add(mock(QuestionnaireEntity.class));

        when(questionnaireRepository.findAll()).thenReturn(list);

        List<QuestionnaireEntity> questionnaires = questionnaireService.findAll();

        assertNotNull(questionnaires);
        assertEquals(1, questionnaires.size());
    }

}
