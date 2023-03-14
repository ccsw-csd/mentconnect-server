package com.ccsw.mentconnect.questionnairepatient.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionnairePatientRepository extends PagingAndSortingRepository<QuestionnairePatientEntity, Long>, JpaSpecificationExecutor<QuestionnairePatientEntity> {

    @Override
    @EntityGraph(attributePaths = {"questionnaire", "patient"})
    List<QuestionnairePatientEntity> findAll();

}
