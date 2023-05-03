package com.ccsw.mentconnect.questionnairequestion.logic;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.ccsw.mentconnect.questionnairequestion.dto.QuestionnaireQuestionDto;
import com.ccsw.mentconnect.questionnairequestion.model.QuestionnaireQuestionEntity;

public interface QuestionnaireQuestionService {
    //QuestionnaireQuestionEntity saveQuestionnaireQuestion(QuestionnaireQuestionDto questionnaireQuestionDto);
    
    List<QuestionnaireQuestionEntity> saveQuestionnaireQuestions(@RequestBody List<QuestionnaireQuestionDto> questionnaireQuestionDto);
}
