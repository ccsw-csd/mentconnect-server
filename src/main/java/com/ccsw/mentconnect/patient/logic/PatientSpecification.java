package com.ccsw.mentconnect.patient.logic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.patient.model.PatientEntity;
import com.ccsw.mentconnect.user.model.UserEntity;

public class PatientSpecification implements Specification<PatientEntity> {

    private static final long serialVersionUID = 1L;

    private final SearchCriteria criteria;

    public PatientSpecification(SearchCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<PatientEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    	if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if(criteria.getOperation().equalsIgnoreCase(";") && criteria.getValue() != null) {
	        Join<UserEntity, PatientEntity> user = root.join("user");
	        return builder.like(user.get("name"),"%" + criteria.getValue()+ "%");
        	        
        } else if(criteria.getOperation().equalsIgnoreCase(",") && criteria.getValue() != null) {
	        Join<UserEntity, PatientEntity> user = root.join("user");
	        return builder.like(user.get("surnames"),"%" + criteria.getValue()+ "%");
        }
        else if(criteria.getOperation().equalsIgnoreCase("-") && criteria.getValue() != null) {
	        Join<UserEntity, PatientEntity> user = root.join("user");
	        return builder.like(user.get("email"),"%" + criteria.getValue()+ "%");
        }
        return null;
    }

}