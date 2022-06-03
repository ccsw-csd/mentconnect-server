package com.ccsw.mentconnect.patient.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;
import com.ccsw.mentconnect.patient.model.PatientEntity;
import com.ccsw.mentconnect.patient.model.PatientRepository;
import com.ccsw.mentconnect.user.logic.UserService;
import com.devonfw.module.beanmapping.common.api.BeanMapper;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserService userService;

    @Autowired
    BeanMapper beanMapper;

    @Override
    public PatientEntity getPatient(Long id) throws EntityNotFoundException {

        return this.patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public PatientEntity savePatient(PatientFullDto patientFullDto) throws AlreadyExistsException {

        if (this.patientRepository.existsByNif(patientFullDto.getNif()))
            throw new AlreadyExistsException();

        PatientEntity patientEntity = this.beanMapper.map(patientFullDto, PatientEntity.class);
        patientEntity.setUser(this.userService.saveUser(patientFullDto.getUser()));

        return this.patientRepository.save(patientEntity);
    }

}
