package com.ccsw.mentconnect.questionnairepatient.logic;

import java.util.List;

import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;

public interface QuestionnairePatientService {
    List<QuestionnairePatientEntity> getQuestionnaireById(Long id) throws EntityNotFoundException;
    List<QuestionnairePatientEntity> findAll();
    QuestionnairePatientEntity saveQuestionnairePatient(QuestionnairePatientDto questionnairePatient);
}
