package com.ccsw.mentconnect.questionnairepatient.model;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireAvailableDto;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;

public interface QuestionnairePatientRepository extends PagingAndSortingRepository<QuestionnairePatientEntity, Long>, JpaSpecificationExecutor<QuestionnairePatientEntity> {

    @Override
    @EntityGraph(attributePaths = {"questionnaire", "patient"})
    List<QuestionnairePatientEntity> findAll();
    
    @EntityGraph(attributePaths = {"questionnaire", "patient"})
    List<QuestionnairePatientEntity> findQuestionnairesByPatientId(@Param("patientId") Long patientId);
}
