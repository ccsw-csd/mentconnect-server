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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSearchDto;
import com.ccsw.mentconnect.questionnaire.logic.QuestionnaireServiceImpl;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireRepository;

@ExtendWith(MockitoExtension.class)
public class QuestionnaireTest {

    @Mock
    private QuestionnaireRepository questionnaireRepository;

    @InjectMocks
    private QuestionnaireServiceImpl questionnaireServiceImpl;

    @Test
    public void findAllShouldReturnAllQuestionnaire() {

        List<QuestionnaireEntity> list = new ArrayList<>();

        list.add(mock(QuestionnaireEntity.class));

        when(questionnaireRepository.findAll()).thenReturn(list);

        List<QuestionnaireEntity> questionnaires = questionnaireServiceImpl.findAll();

        assertNotNull(questionnaires);
        assertEquals(1, questionnaires.size());
    }

    @Test
    public void findPageShouldReturnPageQuestionnaire() {

        List<QuestionnaireEntity> list = new ArrayList<>();

        list.add(mock(QuestionnaireEntity.class));

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));

        when(questionnaireRepository.findAll(dto.getPageable()))
                .thenReturn(new PageImpl<>(list, dto.getPageable(), list.size()));

        Page<QuestionnaireEntity> questionnaire = questionnaireServiceImpl.findPage(dto);

        assertNotNull(questionnaire);
        assertEquals(1, questionnaire.getContent().size());

    }

}
