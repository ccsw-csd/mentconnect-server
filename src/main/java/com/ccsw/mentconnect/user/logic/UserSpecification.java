package com.ccsw.mentconnect.user.logic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import com.ccsw.mentconnect.user.model.UserEntity;

public class UserSpecification implements Specification<UserEntity> {

    private static final long serialVersionUID = 1L;

    private final SearchCriteria criteria;

    public UserSpecification(SearchCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getFirstValue() != null) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getFirstValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getFirstValue());
            }
        }
        return null;
    }

}
