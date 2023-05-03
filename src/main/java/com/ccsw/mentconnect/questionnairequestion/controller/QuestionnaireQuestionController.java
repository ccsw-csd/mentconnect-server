package com.ccsw.mentconnect.questionnairequestion.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.questionnairequestion.dto.QuestionnaireQuestionDto;
import com.ccsw.mentconnect.questionnairequestion.logic.QuestionnaireQuestionService;
import com.ccsw.mentconnect.questionnairequestion.model.QuestionnaireQuestionEntity;

@RequestMapping(value = "/questionnaire-question")
@RestController
public class QuestionnaireQuestionController {

    @Autowired
    private QuestionnaireQuestionService questionnaireQuestionService;

    @Autowired
    BeanMapper beanMapper;
    
    /**@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public QuestionnaireQuestionDto saveQuestionnaireQuestion(@RequestBody QuestionnaireQuestionDto questionnaireQuestionDto){
        return this.beanMapper.map(questionnaireQuestionService.saveQuestionnaireQuestion(questionnaireQuestionDto), QuestionnaireQuestionDto.class);
    }*/
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public List<QuestionnaireQuestionDto> saveQuestionnaireQuestions(@RequestBody List<QuestionnaireQuestionDto> questionnaireQuestionsDto){
        List<QuestionnaireQuestionEntity> savedQuestions = questionnaireQuestionService.saveQuestionnaireQuestions(questionnaireQuestionsDto);
        return savedQuestions.stream()
                .map(q -> beanMapper.map(q, QuestionnaireQuestionDto.class))
                .collect(Collectors.toList());
    }

}
