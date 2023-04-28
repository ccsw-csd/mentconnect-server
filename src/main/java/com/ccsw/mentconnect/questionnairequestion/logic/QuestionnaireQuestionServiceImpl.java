package com.ccsw.mentconnect.questionnairequestion.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.questionnairequestion.dto.QuestionnaireQuestionDto;
import com.ccsw.mentconnect.questionnairequestion.model.QuestionnaireQuestionEntity;
import com.ccsw.mentconnect.questionnairequestion.model.QuestionnaireQuestionRepository;

@Service
public class QuestionnaireQuestionServiceImpl implements QuestionnaireQuestionService {

    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    QuestionnaireQuestionRepository questionnaireQuestionRepository;

    @Transactional
    @Override
    public QuestionnaireQuestionEntity saveQuestionnaireQuestion(QuestionnaireQuestionDto questionnaireQuestionDto) {
        QuestionnaireQuestionEntity questionnaireQuestionEntity = this.beanMapper.map(questionnaireQuestionDto, QuestionnaireQuestionEntity.class);
        return this.questionnaireQuestionRepository.save(questionnaireQuestionEntity);
    }

}