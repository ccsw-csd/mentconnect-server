package com.ccsw.mentconnect.patient.logic;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.patient.dto.PatientDtoFull;
import com.ccsw.mentconnect.patient.model.PatientEntity;

public interface PatientService {

    PatientEntity savePatient(PatientDtoFull patientDtoFull) throws AlreadyExistsException;

    PatientEntity getPatient(Long id) throws EntityNotFoundException;
}
