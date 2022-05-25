package com.ccsw.mentconnect.questionnaire.model;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionnaireRepository extends PagingAndSortingRepository<QuestionnaireEntity, Long> {

    @Override
    @EntityGraph(attributePaths = { "user" })
    List<QuestionnaireEntity> findAll();

}
