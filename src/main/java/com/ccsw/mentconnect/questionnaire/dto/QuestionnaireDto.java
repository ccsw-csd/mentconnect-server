package com.ccsw.mentconnect.questionnaire.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.ccsw.mentconnect.patient.dto.PatientDto;
import com.ccsw.mentconnect.questionnairequestion.dto.QuestionnaireQuestionSimpleDto;
import com.ccsw.mentconnect.user.dto.UserDto;

public class QuestionnaireDto implements Serializable {

    private Long id;

    private String description;

    private List<QuestionnaireQuestionSimpleDto> questions;

    private List<PatientDto> patients;

    private UserDto user;

    private LocalDate createDate;

    private LocalDate lastEditDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionnaireQuestionSimpleDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionnaireQuestionSimpleDto> questions) {
        this.questions = questions;
    }

    public List<PatientDto> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDto> patients) {
        this.patients = patients;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(LocalDate lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

}