package com.ccsw.mentconnect.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;
import com.ccsw.mentconnect.patient.dto.PatientSearchDto;
import com.ccsw.mentconnect.patient.logic.PatientService;
import com.ccsw.mentconnect.common.mapper.BeanMapper;


@RequestMapping(value = "/patient")
@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public PatientFullDto getPatient(@PathVariable Long id) throws EntityNotFoundException {

        return this.beanMapper.map(patientService.getPatient(id), PatientFullDto.class);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public PatientFullDto savePatient(@RequestBody PatientFullDto patientFullDto) throws AlreadyExistsException {

        return this.beanMapper.map(patientService.savePatient(patientFullDto), PatientFullDto.class);
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<PatientFullDto> findAll() {

        return this.beanMapper.mapList(this.patientService.findAll(), PatientFullDto.class);
    }

    @PreAuthorize("hasAuthority('ADMIN', 'STAFF')")
    @RequestMapping(path = "/findPage", method = RequestMethod.POST)
    public Page<PatientFullDto> findPage(@RequestBody PatientSearchDto dto) {

        return this.beanMapper.mapPage(patientService.findPage(dto), PatientFullDto.class);
    }

    @PreAuthorize("hasAuthority('ADMIN', 'STAFF')")
    @RequestMapping(path = "/findFilter/{filter}", method = RequestMethod.GET)
    public List<PatientFullDto> findFilter(@PathVariable String filter) {
    	    	
        return this.beanMapper.mapList(patientService.findFilter(filter), PatientFullDto.class);
    }
}
