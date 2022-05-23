package com.ccsw.mentconnect.questionnaire.logic;

import java.util.List;

import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;

public interface QuestionnaireService {
	
	List<QuestionnaireEntity> findAll();

}
