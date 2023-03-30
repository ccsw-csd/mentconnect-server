package com.ccsw.mentconnect.questionnairepatient.logic;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;
import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;

import org.springframework.data.jpa.domain.Specification;

public class QuestionnairePatientSpecification implements Specification<QuestionnairePatientEntity> {
    private static final long serialVersionUID = 1L;
    private SearchCriteria criteria;

    public QuestionnairePatientSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<QuestionnairePatientEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()).as(LocalDate.class),
                    (LocalDate) criteria.getFirstValue());
        } else if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()).as(LocalDate.class),
                    (LocalDate) criteria.getFirstValue());
        } else if (criteria.getOperation().equalsIgnoreCase("<>")) {
            return builder.between(root.get(criteria.getKey()).as(LocalDate.class), (LocalDate) criteria.getFirstValue(),
                    (LocalDate) criteria.getSecondValue());
        } else if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getFirstValue() != null) {
            Path<String> path = getPath(root);
            if (path.getJavaType() == String.class) {
                return builder.like(path, "%" + criteria.getFirstValue() + "%");
            } else {
                return builder.equal(path, criteria.getFirstValue());
            }
        }

        return null;
    }
    

    private Path<String> getPath(Root<QuestionnairePatientEntity> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }

}