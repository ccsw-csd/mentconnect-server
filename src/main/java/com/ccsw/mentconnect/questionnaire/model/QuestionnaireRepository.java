package com.ccsw.mentconnect.questionnaire.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionnaireRepository extends PagingAndSortingRepository<QuestionnaireEntity, Long>, JpaSpecificationExecutor<QuestionnaireEntity> {

    @Override
    List<QuestionnaireEntity> findAll();

    @Override
    @EntityGraph(attributePaths = {"user", "questions", "patients"})
    Page<QuestionnaireEntity> findAll(Specification spec, Pageable pageable);

}
