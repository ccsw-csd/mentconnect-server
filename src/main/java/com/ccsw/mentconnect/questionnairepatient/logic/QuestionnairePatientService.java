package com.ccsw.mentconnect.questionnairepatient.logic;

import java.util.List;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;

public interface QuestionnairePatientService {

    List<QuestionnairePatientEntity> getQuestionnaireByPatientId(Long patientId) throws EntityNotFoundException;

    List<QuestionnaireEntity> getQuestionnaireAvailable(Long patientId);
    
    List<QuestionnairePatientEntity> findQuestionnairesAssigned(QuestionnairePatientDto questionnairePatient);

    Boolean checkQuestionnaireAssignable(QuestionnairePatientDto questionnairePatient);

    QuestionnairePatientEntity saveQuestionnairePatient(QuestionnairePatientDto questionnairePatient);

    void delete(Long id);
    
}
