package com.ccsw.mentconnect.user.logic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ccsw.mentconnect.user.model.UserEntity;

public class UserSpecification implements Specification<UserEntity> {

    private static final long serialVersionUID = 1L;
    private static SearchCriteria criteria;

    public UserSpecification(SearchCriteria searchCriteria) {

        UserSpecification.criteria = searchCriteria;
    }

    public static Specification<UserEntity> getSpecName(String name) {

        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.conjunction();
            }
            if (criteria.getOperation().equalsIgnoreCase("==")) {

                return criteriaBuilder.equal(root.<String>get(criteria.getFilterKey().toString()), criteria.getDatos());
            }

            return null;

        };

    }

    public static Specification<UserEntity> getSpecId(Long id) {

        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }
            // else {
            // String convertir = String.valueOf(id);
            // return criteriaBuilder.equal(root.get(convertir), root);
            if (criteria.getOperation().equalsIgnoreCase(":")) {

                return criteriaBuilder.equal(root.<Integer>get(criteria.getFilterKey()).as(Integer.class),
                        (Integer) criteria.getDatos());

            }
            return null;
        };

    }

    public static Specification<UserEntity> getSpecUsername(String username) {

        return (root, query, criteriaBuilder) -> {
            if (username == null) {
                return criteriaBuilder.conjunction();
            }
            if (criteria.getOperation().equalsIgnoreCase("==")) {

                return criteriaBuilder.equal(root.<String>get(criteria.getFilterKey().toString()), criteria.getDatos());

            }
            return null;
        };

    }

    public static Specification<UserEntity> getSpecSurname(String surnames) {

        return (root, query, criteriaBuilder) -> {
            if (surnames == null) {
                return criteriaBuilder.conjunction();
            }
            if (criteria.getOperation().equalsIgnoreCase("==")) {

                return criteriaBuilder.equal(root.<String>get(criteria.getFilterKey().toString()), criteria.getDatos());

            }
            return null;

        };

    }

    public static Specification<UserEntity> getSpecEmail(String email) {

        return (root, query, criteriaBuilder) -> {
            if (email == null) {
                return criteriaBuilder.conjunction();
            }
            if (criteria.getOperation().equalsIgnoreCase("==")) {

                return criteriaBuilder.equal(root.<String>get(criteria.getFilterKey().toString()), criteria.getDatos());

            }

            return null;

        };

    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        // if (criteria.getOperation().equalsIgnoreCase("==")) {

        // return builder.equal(root.<String>get(criteria.getFilterKey().toString()),
        // criteria.getDatos());
        // } else if (criteria.getOperation().equalsIgnoreCase(":")) {
        // return
        // builder.equal(root.<Integer>get(criteria.getFilterKey()).as(Integer.class),
        // (Integer) criteria.getDatos());

        // }
        return null;
    }

}
