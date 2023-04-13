package com.ccsw.mentconnect.questionnairepatient.logic;

import java.util.List;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;

public interface QuestionnairePatientService {
    List<QuestionnairePatientEntity> getQuestionnaireByPatientId(Long patientId) throws EntityNotFoundException;
    
    QuestionnairePatientEntity saveQuestionnairePatient(QuestionnairePatientDto questionnairePatient);
    
    void delete(Long id);
    
    List<QuestionnairePatientEntity> findQuestionnairesAssigned(QuestionnairePatientDto questionnairePatien);
    
    List<QuestionnaireEntity> questionnaireAvailable(Long patientId);

    Boolean checkQuestionnaireAssignable(QuestionnairePatientDto questionnairePatient);
    
}
