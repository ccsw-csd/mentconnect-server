package com.ccsw.mentconnect.diary.model;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends PagingAndSortingRepository<DiaryEntity, Long>, JpaSpecificationExecutor<DiaryEntity> {
    
    //@EntityGraph(attributePaths = {"questionnaire","questionnaire.patients.user","questionnaire.questions","questionnaire.questions.answerType","questionnaire.user"})
    List<DiaryEntity> findDiaryByPatientId(@Param("patientId") Long patientId);
}
