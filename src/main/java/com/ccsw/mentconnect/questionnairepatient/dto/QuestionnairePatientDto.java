package com.ccsw.mentconnect.questionnairepatient.dto;

import java.time.LocalDate;

import com.ccsw.mentconnect.patient.dto.PatientDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;

public class QuestionnairePatientDto {

    private Long id;

    private QuestionnaireDto questionnaire;

    private PatientDto patient;

    private LocalDate startDate;

    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionnaireDto getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(QuestionnaireDto questionnaire) {
        this.questionnaire = questionnaire;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    

}
