package com.ccsw.mentconnect.answertype.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;
import com.ccsw.mentconnect.answertype.model.AnswerTypeRepository;

@Service
public class AnswerTypeServiceImpl implements AnswerTypeService {
    @Autowired
    AnswerTypeRepository answerTypeRepository;

    public List<AnswerTypeEntity> findByDescription(String description) {
        return answerTypeRepository.findByDescription(description);
    }


}
