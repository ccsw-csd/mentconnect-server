package com.ccsw.mentconnect.patient.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.patient.dto.PatientDto;
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
    @RequestMapping(path = "", method = RequestMethod.POST)
    public PatientDto savePatient(@RequestBody PatientDto patientDto) throws AlreadyExistsException {

        return this.beanMapper.map(this.patientService.savePatient(patientDto), PatientDto.class);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public PatientDto getPatient(@PathVariable Long id) throws EntityNotFoundException {

        return this.beanMapper.map(this.patientService.getPatient(id), PatientDto.class);
    }
}
