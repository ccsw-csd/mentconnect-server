package com.ccsw.mentconnect.questionnairepatient.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.patient.model.PatientEntity;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientRepository;

@Service
public class QuestionnairePatientServiceImpl implements QuestionnairePatientService {

    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    QuestionnairePatientRepository questionnairePatientRepository;
    
    @Override
    public List<QuestionnairePatientEntity> getQuestionnaireById(Long id) throws EntityNotFoundException {

        return questionnairePatientRepository.findQuestionnairesByPatientId(id);
    }

    @Override
    public List<QuestionnairePatientEntity> findAll() {

        return questionnairePatientRepository.findAll();
    }

    

}
