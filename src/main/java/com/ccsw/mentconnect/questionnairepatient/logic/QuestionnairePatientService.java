package com.ccsw.mentconnect.questionnairepatient.logic;

import java.util.Date;
import java.util.List;

import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;

public interface QuestionnairePatientService {
    List<QuestionnairePatientEntity> getQuestionnaireByPatientId(Long patientId) throws EntityNotFoundException;
    
//    List<QuestionnairePatientEntity> findAll();
    
    QuestionnairePatientEntity saveQuestionnairePatient(QuestionnairePatientDto questionnairePatient);
    
    void delete(Long id);
    
    List<QuestionnairePatientEntity> questionnaireAssigned(Long idPatient, Date startDate, Date endDate);
    
    List<QuestionnaireEntity> questionnaireAvailable(Long patientId) throws EntityNotFoundException;
    
}
