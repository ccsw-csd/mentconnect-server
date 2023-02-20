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
    		String key = criteria.getKey();
    		String[] separator = key.split("[.]", 0);
    		if(separator[0].equalsIgnoreCase("user")) {
    			Join<UserEntity, PatientEntity> user = root.join("user");
                if (user.get(separator[1]).getJavaType() == String.class ) { 
                    return builder.like(user.<String>get(separator[1]), "%" + criteria.getValue() + "%");
                } else {
                    return builder.equal(root.get(separator[1]), criteria.getValue());
                }
    		}else {
                if (root.get(criteria.getKey()).getJavaType() == String.class ) { 
                    return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
                
    		}
    	}
        return null;
    }
}