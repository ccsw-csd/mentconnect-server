package com.ccsw.mentconnect.questionnaire.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireRepository;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	QuestionnaireRepository questionnaireRepository;
	
	@Override
	public List<QuestionnaireEntity> findAll() {
		// TODO Auto-generated method stub
		return questionnaireRepository.findAll();
	}
	
	

}
