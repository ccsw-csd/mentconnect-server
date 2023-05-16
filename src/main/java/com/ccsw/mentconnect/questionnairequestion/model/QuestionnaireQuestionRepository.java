package com.ccsw.mentconnect.questionnairequestion.model;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionnaireQuestionRepository extends PagingAndSortingRepository<QuestionnaireQuestionEntity, Long>, JpaSpecificationExecutor<QuestionnaireQuestionEntity> {
    
}
