package com.ccsw.mentconnect.user.logic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ccsw.mentconnect.user.model.UserEntity;

public class UserSpecification implements Specification<UserEntity> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final SearchCriteria criteria;

    public UserSpecification(SearchCriteria searchCriteria) {

        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase("==")) {
            return builder.equal(root.<String>get(criteria.getFilterKey()), criteria.getDatos().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            return builder.equal(root.<Integer>get(criteria.getFilterKey()).as(Integer.class),
                    (Integer) criteria.getDatos());

        }

        return null;
    }
}
