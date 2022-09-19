package com.ccsw.mentconnect.questionnaire.dto;

import java.time.LocalDate;

import com.ccsw.mentconnect.user.dto.UserDto;

public class QuestionnaireDto {

    private Long id;

    private String description;

    private Integer questionsNumber;

    private Integer patientsNumber;

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

    public Integer getQuestions() {
        return questionsNumber;
    }

    public void setQuestions(Integer questions) {
        this.questionsNumber = questions;
    }

    public Integer getPatients() {
        return patientsNumber;
    }

    public void setPatients(Integer patients) {
        this.patientsNumber = patients;
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
