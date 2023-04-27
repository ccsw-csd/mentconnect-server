package com.ccsw.mentconnect.answertype.logic;

import java.util.List;

import com.ccsw.mentconnect.answertype.dto.AnswerTypeDto;
import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;
import com.ccsw.mentconnect.answertypevalue.model.AnswerTypeValueEntity;

public interface AnswerTypeService {
	List<AnswerTypeEntity> findByDescription(String description);
}
