package com.ccsw.mentconnect.questionnairepatient.logic;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;

public interface QuestionnairePatientService {
    List<QuestionnairePatientEntity> getQuestionnaireById(Long id) throws EntityNotFoundException;
    List<QuestionnairePatientEntity> findAll();
    QuestionnairePatientEntity saveQuestionnairePatient(QuestionnairePatientDto questionnairePatient);
    void delete(Long id);
    boolean questionnaireAssigned(Long idPatient, LocalDate startDate, LocalDate endDate);
}
