package com.ccsw.mentconnect.questionnaire.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionnaireRepository extends PagingAndSortingRepository<QuestionnaireEntity, Long>, JpaSpecificationExecutor<QuestionnaireEntity> {

    @Override
    @EntityGraph(attributePaths = {"user", "questions", "questions.question", "questions.alertConfigAnswerType", "questions.weekDays", "questions.question.answerType" })
    Optional<QuestionnaireEntity> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"user", "questions", "questions.question"})
    List<QuestionnaireEntity> findAll();

    @EntityGraph(attributePaths = {"user", "questions", "questions.question", "patients", "patients.user"})
    Page<QuestionnaireEntity> findAll(Specification spec, Pageable pageable);

    Boolean existsByDescription(String description);

}
