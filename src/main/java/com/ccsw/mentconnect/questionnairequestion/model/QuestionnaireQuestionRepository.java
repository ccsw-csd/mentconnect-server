package com.ccsw.mentconnect.questionnairequestion.model;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface QuestionnaireQuestionRepository extends PagingAndSortingRepository<QuestionnaireQuestionEntity, Long>, JpaSpecificationExecutor<QuestionnaireQuestionEntity> {
    
}
