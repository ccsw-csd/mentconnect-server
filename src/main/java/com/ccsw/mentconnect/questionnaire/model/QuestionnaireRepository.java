package com.ccsw.mentconnect.questionnaire.model;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionnaireRepository extends PagingAndSortingRepository<QuestionnaireEntity, Long> {
	
	//Preguntar
	@Override
	List<QuestionnaireEntity> findAll();

}
