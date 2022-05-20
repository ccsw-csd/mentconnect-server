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

    public static Specification<UserEntity> searchName(String name) {
        if (name == null) {
            return null;

        } else {
            return (root, query, cb) -> {
                return cb.like(root.get(UserEntity.ATT_NAME), "%" + name + "%");
            };
        }
    }

    public static Specification<UserEntity> searchId(Long id) {
        if (id == null) {
            return null;

        } else {
            return (root, query, cb) -> {
                return cb.equal(root.get(UserEntity.ATT_ID), id);
            };
        }
    }

    public static Specification<UserEntity> searchSurnames(String surnames) {
        if (surnames == null) {
            return null;

        } else {
            return (root, query, cb) -> {
                return cb.like(root.get(UserEntity.ATT_SURNAMES), "%" + surnames + "%");
            };
        }
    }

    public static Specification<UserEntity> searchUsername(String username) {
        if (username == null) {
            return null;

        } else {
            return (root, query, cb) -> {
                return cb.like(root.get(UserEntity.ATT_USERNAME), "%" + username + "%");
            };
        }
    }

    public static Specification<UserEntity> searchEmail(String email) {
        if (email == null) {
            return null;

        } else {
            return (root, query, cb) -> {
                return cb.like(root.get(UserEntity.ATT_SURNAMES), "%" + email + "%");
            };

        }
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

    // @Override
    // public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query,
    // CriteriaBuilder builder) {

    // if (criteria.getOperation().equalsIgnoreCase("==")) {
    // return builder.equal(root.<String>get(criteria.getFilterKey()),
    // criteria.getDatos().toString());
    // } else if (criteria.getOperation().equalsIgnoreCase(":")) {
    // return
    // builder.equal(root.<Integer>get(criteria.getFilterKey()).as(Integer.class),
    // (Integer) criteria.getDatos());

    // }

    // return null;
    // }
}
