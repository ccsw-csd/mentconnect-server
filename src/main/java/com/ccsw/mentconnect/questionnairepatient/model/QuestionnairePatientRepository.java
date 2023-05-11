package com.ccsw.mentconnect.questionnairepatient.model;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface QuestionnairePatientRepository extends PagingAndSortingRepository<QuestionnairePatientEntity, Long>, JpaSpecificationExecutor<QuestionnairePatientEntity> {

    @Override
    @EntityGraph(attributePaths = {"questionnaire", "patient"})
    List<QuestionnairePatientEntity> findAll();
    
    @EntityGraph(attributePaths = {"questionnaire","questionnaire.patients.user","questionnaire.questions","questionnaire.user"})
    List<QuestionnairePatientEntity> findQuestionnairesByPatientId(@Param("patientId") Long patientId);
}
