package com.ccsw.mentconnect.questionnaire.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.ccsw.mentconnect.questionnairequestion.dto.QuestionnaireQuestionSimpleDto;
import com.ccsw.mentconnect.user.dto.UserDto;

public class QuestionnaireSimpleDto implements Serializable {

    private Long id;

    private String description;
    
    private Set<QuestionnaireQuestionSimpleDto> questions;

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
    
    public Set<QuestionnaireQuestionSimpleDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionnaireQuestionSimpleDto> questions) {
        this.questions = questions;
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