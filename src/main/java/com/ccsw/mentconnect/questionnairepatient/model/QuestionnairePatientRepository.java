package com.ccsw.mentconnect.questionnairepatient.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface QuestionnairePatientRepository extends PagingAndSortingRepository<QuestionnairePatientEntity, Long>, JpaSpecificationExecutor<QuestionnairePatientEntity> {

    @Override
    @EntityGraph(attributePaths = {"questionnaire", "patient"})
    List<QuestionnairePatientEntity> findAll();
    
    @Query("SELECT q FROM QuestionnairePatientEntity q WHERE (q.patient.id = :id)")
    List<QuestionnairePatientEntity> findQuestionnairesByPatientId(@Param("id") Long id);

    //@Query("SELECT CASE WHEN COUNT(q) >= 1 THEN true ELSE false END FROM QuestionnairePatientEntity q WHERE (q.patient.id = :patientId)  AND (:startDate BETWEEN q.startDate AND q.endDate) OR  (:endDate BETWEEN q.startDate AND q.endDate) OR (:startDate <= q.startDate AND :endDate >= q.endDate) OR (:startDate >= q.startDate AND :endDate <= q.endDate)")
   // boolean existsByPatientByStartDateByEndDate(@Param("patientId") Long patientId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
