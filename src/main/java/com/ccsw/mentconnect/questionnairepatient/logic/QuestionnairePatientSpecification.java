package com.ccsw.mentconnect.questionnairepatient.logic;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;
import com.ccsw.mentconnect.common.criteria.SearchCriteria;
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
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()).as(Date.class),
                    (Date) criteria.getFirstValue());
        } else if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()).as(Date.class),
                    (Date) criteria.getFirstValue());
        } else if (criteria.getOperation().equalsIgnoreCase("<>")) {
            return builder.between(root.get(criteria.getKey()).as(Date.class), (Date) criteria.getFirstValue(),
                    (Date) criteria.getSecondValue());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getFirstValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getFirstValue());
            }
        }

        return null;
    }

}