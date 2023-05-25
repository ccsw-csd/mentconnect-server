package com.ccsw.mentconnect.answertypevalue.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.answertype.dto.AnswerTypeDto;
import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;
import com.ccsw.mentconnect.answertypevalue.dto.AnswerTypeValueDto;
import com.ccsw.mentconnect.answertypevalue.model.AnswerTypeValueEntity;
import com.ccsw.mentconnect.answertypevalue.model.AnswerTypeValueRepository;

@Service
public class AnswerTypeValueServiceImpl implements AnswerTypeValueService {

    @Autowired
    AnswerTypeValueRepository answerTypeValueRepository;

    @Override
    public List<AnswerTypeValueEntity> findAll() {

        return this.answerTypeValueRepository.findAll();
    }
    
    @Override
    public List<AnswerTypeValueEntity> findByAnswerType(AnswerTypeEntity answerTypeEntity) {

        return this.answerTypeValueRepository.findByAnswerType(answerTypeEntity);
    }

    @Override
    public List<AnswerTypeValueEntity> findByAnswerTypeDescription(String description) {

        return answerTypeValueRepository.findByAnswerTypeDescription(description);
    }

}
