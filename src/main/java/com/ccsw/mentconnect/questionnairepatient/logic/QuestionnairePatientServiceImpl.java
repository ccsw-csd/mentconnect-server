package com.ccsw.mentconnect.questionnairepatient.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
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

    @Transactional
    @Override
    public QuestionnairePatientEntity saveQuestionnairePatient(QuestionnairePatientDto questionnairePatient) {
        QuestionnairePatientEntity questionnairePatientEntity = this.beanMapper.map(questionnairePatient, QuestionnairePatientEntity.class);
        return this.questionnairePatientRepository.save(questionnairePatientEntity);
    }

}
