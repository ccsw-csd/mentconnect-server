package com.ccsw.mentconnect.questionary.model;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionnaireRepository extends PagingAndSortingRepository<QuestionnaireEntity, Long> {
	
	//Preguntar
	@Override
	List<QuestionnaireEntity> findAll();

}
