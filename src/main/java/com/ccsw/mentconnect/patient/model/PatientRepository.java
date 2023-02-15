package com.ccsw.mentconnect.patient.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;

public interface PatientRepository extends PagingAndSortingRepository<PatientEntity, Long>, JpaSpecificationExecutor<PatientEntity>{
	
	List<PatientEntity> findAll();

    boolean existsByNif(String nif);



}
