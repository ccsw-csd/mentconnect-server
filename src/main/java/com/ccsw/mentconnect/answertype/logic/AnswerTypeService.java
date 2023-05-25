package com.ccsw.mentconnect.answertype.logic;

import java.util.List;
import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;

public interface AnswerTypeService {

	List<AnswerTypeEntity> findByDescription(String description);
}
