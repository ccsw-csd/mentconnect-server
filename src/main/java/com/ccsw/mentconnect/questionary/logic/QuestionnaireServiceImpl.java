package com.ccsw.mentconnect.questionary.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.questionary.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionary.model.QuestionnaireRepository;

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
