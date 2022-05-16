package com.ccsw.mentconnect.user.logic;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.mapping.List;
import org.springframework.security.core.userdetails.User;

import com.ccsw.mentconnect.user.model.UserEntity;

public class UserSpecificationBuilder<UserSpecificationsBuilder> {

	SearchCriteria criteria;

	
	public UserSpecificationBuilder(SearchCriteria criteria) {
	
		this.criteria = criteria;
	}


	 public Predicate toPredicate
	      (Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
	 
	        if (criteria.getOperation().equalsIgnoreCase("==")) {
	            return builder.equal(
	              root.<String> get(criteria.getKey()), criteria.getDatos().toString());
	        } 
	      
	        return null;
	    }
	}
