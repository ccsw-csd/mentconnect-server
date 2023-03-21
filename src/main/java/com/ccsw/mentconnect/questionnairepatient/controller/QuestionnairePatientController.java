package com.ccsw.mentconnect.questionnairepatient.controller;

import java.time.LocalDate;
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

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
import com.ccsw.mentconnect.questionnairepatient.logic.QuestionnairePatientService;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;

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
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public QuestionnairePatientDto saveQuestionnairePatient(@RequestBody QuestionnairePatientDto questionnairePatient){

        return this.beanMapper.map(questionnairePatientService.saveQuestionnairePatient(questionnairePatient), QuestionnairePatientDto.class);
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteQuestionnairePatient(@PathVariable Long id){
        this.questionnairePatientService.delete(id);
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = { "/questionnaire-assigned" }, method = RequestMethod.GET)
    public List<QuestionnairePatientEntity> questionnaireAssigned(@RequestParam(value = "patientId", required = true)Long idPatient,@RequestParam(value = "startDate", required = true)@DateTimeFormat(pattern = "MM-dd-yyyy") Date startDate,
            @RequestParam(value = "endDate", required = true)@DateTimeFormat(pattern = "MM-dd-yyyy") Date endDate){  

        return this.questionnairePatientService.questionnaireAssigned(idPatient,startDate, endDate);
    }
    

}
