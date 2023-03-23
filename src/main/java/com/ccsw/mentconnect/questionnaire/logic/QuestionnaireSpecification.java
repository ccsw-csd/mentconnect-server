package com.ccsw.mentconnect.questionnaire.logic;

import javax.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;

public class QuestionnaireSpecification implements Specification<QuestionnaireEntity> {

    private static final long serialVersionUID = 1L;

    private final SearchCriteria criteria;

    public QuestionnaireSpecification(SearchCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<QuestionnaireEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getFirstValue() != null) {
            Path<String> path = getPath(root);
            if (path.getJavaType() == String.class) {
                return builder.like(path, "%" + criteria.getFirstValue() + "%");
            } else {
                return builder.equal(path, criteria.getFirstValue());
            }
        }
        return null;
    }

    private Path<String> getPath(Root<QuestionnaireEntity> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }

}
