package com.ccsw.mentconnect.answertypevalue.logic;

import java.util.List;

import com.ccsw.mentconnect.answertype.dto.AnswerTypeDto;
import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;
import com.ccsw.mentconnect.answertypevalue.model.AnswerTypeValueEntity;

public interface AnswerTypeValueService {

    List<AnswerTypeValueEntity> findAll();
    
    List<AnswerTypeValueEntity> findByAnswerType(AnswerTypeEntity answerTypeEntity);

    List<AnswerTypeValueEntity> findByAnswerTypeDescription(String description);
}
