package com.ccsw.mentconnect.question.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.question.model.QuestionEntity;
import com.ccsw.mentconnect.question.model.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<QuestionEntity> findAll() {
        return questionRepository.findAll();
    }

}
