package com.ccsw.mentconnect.patient.logic;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;
import com.ccsw.mentconnect.patient.model.PatientEntity;

public interface PatientService {

    PatientEntity savePatient(PatientFullDto patientFullDto) throws AlreadyExistsException;

    PatientEntity getPatient(Long id) throws EntityNotFoundException;

}
