package com.ccsw.mentconnect.question.logic;

import java.util.List;

import com.ccsw.mentconnect.question.model.QuestionEntity;

public interface QuestionService {
    List<QuestionEntity> findAll();

}
