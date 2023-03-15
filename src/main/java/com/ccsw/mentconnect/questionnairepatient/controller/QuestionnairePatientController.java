package com.ccsw.mentconnect.questionnairepatient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
import com.ccsw.mentconnect.questionnairepatient.logic.QuestionnairePatientService;

@RequestMapping(value = "/questionnaire_patient")
@RestController
public class QuestionnairePatientController {

    @Autowired
    private QuestionnairePatientService questionnairePatientService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<QuestionnairePatientDto> getQuestionnaireById(@PathVariable Long id) throws EntityNotFoundException {

        return this.beanMapper.mapList(questionnairePatientService.getQuestionnaireById(id), QuestionnairePatientDto.class);
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<QuestionnairePatientDto> findAll() {

        return this.beanMapper.mapList(questionnairePatientService.findAll(), QuestionnairePatientDto.class);
    }

}
