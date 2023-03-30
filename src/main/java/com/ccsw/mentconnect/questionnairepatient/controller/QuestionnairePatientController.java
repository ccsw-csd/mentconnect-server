package com.ccsw.mentconnect.questionnairepatient.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireAvailableDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
import com.ccsw.mentconnect.questionnairepatient.logic.QuestionnairePatientService;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;

@RequestMapping(value = "/questionnaire-patient")
@RestController
public class QuestionnairePatientController {

    @Autowired
    private QuestionnairePatientService questionnairePatientService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/{patientId}", method = RequestMethod.GET)
    public List<QuestionnairePatientDto> getQuestionnaireByPatientId(@PathVariable Long patientId) throws EntityNotFoundException {

        return this.beanMapper.mapList(questionnairePatientService.getQuestionnaireByPatientId(patientId), QuestionnairePatientDto.class);
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public QuestionnairePatientDto saveQuestionnairePatient(@RequestBody QuestionnairePatientDto questionnairePatient){

        return this.beanMapper.map(questionnairePatientService.saveQuestionnairePatient(questionnairePatient), QuestionnairePatientDto.class);
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteQuestionnairePatient(@PathVariable Long id) {

        this.questionnairePatientService.delete(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/check-questionnaire-assignable/", method = RequestMethod.POST)
    public Boolean checkQuestionnaireAssignable(@RequestBody QuestionnairePatientDto questionnairePatient) {
        return questionnairePatientService.checkQuestionnaireAssignable(questionnairePatient);
    }
    
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = { "/questionnaire-available/{patientId}" }, method = RequestMethod.GET)
    public List<QuestionnaireAvailableDto> questionnaireAvailable(@PathVariable Long patientId) {

        return this.beanMapper.mapList(questionnairePatientService.questionnaireAvailable(patientId), QuestionnaireAvailableDto.class);
    }

}
