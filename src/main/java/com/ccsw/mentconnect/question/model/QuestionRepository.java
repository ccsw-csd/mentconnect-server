package com.ccsw.mentconnect.question.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<QuestionEntity, Long>, JpaSpecificationExecutor<QuestionEntity> {

    @Override
    //@EntityGraph(attributePaths = {"user", "questions", "questions.answerType"})
    List<QuestionEntity> findAll();

}
