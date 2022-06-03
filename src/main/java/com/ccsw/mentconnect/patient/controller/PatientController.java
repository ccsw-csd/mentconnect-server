package com.ccsw.mentconnect.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.patient.dto.PatientDtoFull;
import com.ccsw.mentconnect.patient.logic.PatientService;
import com.devonfw.module.beanmapping.common.api.BeanMapper;

@RequestMapping(value = "/patient")
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public PatientDtoFull getPatient(@PathVariable Long id) throws EntityNotFoundException {

        return this.beanMapper.map(this.patientService.getPatient(id), PatientDtoFull.class);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public PatientDtoFull savePatient(@RequestBody PatientDtoFull patientDtoFull) throws AlreadyExistsException {

        return this.beanMapper.map(this.patientService.savePatient(patientDtoFull), PatientDtoFull.class);
    }
}
