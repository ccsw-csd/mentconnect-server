package com.ccsw.mentconnect.answertypevalue.model;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ccsw.mentconnect.answertype.dto.AnswerTypeDto;
import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;
import com.ccsw.mentconnect.role.model.RoleEntity;
import com.ccsw.mentconnect.role.model.RoleTypeEnum;

public interface AnswerTypeValueRepository extends PagingAndSortingRepository<AnswerTypeValueEntity, Long>, JpaSpecificationExecutor<AnswerTypeValueEntity> {
    List<AnswerTypeValueEntity> findAll();
    List<AnswerTypeValueEntity> findByAnswerType(AnswerTypeEntity answerTypeEntity);
    List<AnswerTypeValueEntity> findByAnswerTypeDescription(String description);
}
