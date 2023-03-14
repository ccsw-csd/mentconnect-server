package com.ccsw.mentconnect.questionnairepatient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;
import com.ccsw.mentconnect.questionnaire.logic.QuestionnaireService;

@RequestMapping(value = "/questionnaire_patient")
@RestController
public class QuestionnairePatientController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<QuestionnaireDto> findAll() {

        return this.beanMapper.mapList(questionnaireService.findAll(), QuestionnaireDto.class);
    }

}
