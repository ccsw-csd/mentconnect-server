package com.ccsw.mentconnect.patient.logic;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;
import com.ccsw.mentconnect.patient.dto.PatientSearchDto;
import com.ccsw.mentconnect.patient.model.PatientEntity;

public interface PatientService {

    PatientEntity savePatient(PatientFullDto patientFullDto) throws AlreadyExistsException;

    PatientEntity getPatient(Long id) throws EntityNotFoundException;
    
    List<PatientEntity> findAll();

    Page<PatientEntity> findPage(PatientSearchDto dto);

    List<PatientEntity> findFilter(String filter);
    
    PatientEntity modifyPatient(PatientFullDto patientFullDto) throws EntityNotFoundException, AlreadyExistsException;

}
