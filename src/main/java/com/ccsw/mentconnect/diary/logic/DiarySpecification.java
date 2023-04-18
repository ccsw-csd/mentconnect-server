package com.ccsw.mentconnect.diary.logic;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.diary.model.DiaryEntity;

import org.springframework.data.jpa.domain.Specification;

public class DiarySpecification implements Specification<DiaryEntity> {
    private static final long serialVersionUID = 1L;
    private SearchCriteria criteria;

    public DiarySpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<DiaryEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()).as(Date.class),
                    (Date) criteria.getFirstValue());
        } else if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()).as(Date.class),
                    (Date) criteria.getFirstValue());
        } else if (criteria.getOperation().equalsIgnoreCase("<>")) {
            return builder.between(root.get(criteria.getKey()).as(Date.class), (Date) criteria.getFirstValue(),
                    (Date) criteria.getSecondValue());
        }
        
        return null;
    }
    

}