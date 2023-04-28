package com.ccsw.mentconnect.questionnairequestion.logic;

import com.ccsw.mentconnect.questionnairequestion.dto.QuestionnaireQuestionDto;
import com.ccsw.mentconnect.questionnairequestion.model.QuestionnaireQuestionEntity;

public interface QuestionnaireQuestionService {
    QuestionnaireQuestionEntity saveQuestionnaireQuestion(QuestionnaireQuestionDto questionnaireQuestionDto);
}
