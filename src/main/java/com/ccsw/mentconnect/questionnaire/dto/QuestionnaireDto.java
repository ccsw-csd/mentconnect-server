package com.ccsw.mentconnect.questionnaire.dto;

import java.time.LocalDate;
import java.util.List;

import com.ccsw.mentconnect.patient.dto.PatientDto;
import com.ccsw.mentconnect.question.dto.QuestionDto;
import com.ccsw.mentconnect.user.dto.UserDto;

public class QuestionnaireDto {

    private Long id;

    private String description;

    private List<QuestionDto> questions;

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

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

//    public Integer getQuestionsNumber() {
//        return questions.size();
//    }

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