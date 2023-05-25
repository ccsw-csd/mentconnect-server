package com.ccsw.mentconnect.questionnaire.dto;

import com.ccsw.mentconnect.questionnairequestion.dto.QuestionnaireQuestionMinimalDto;
import com.ccsw.mentconnect.user.dto.UserDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class QuestionnaireMinimalSimpleDto implements Serializable {

    private Long id;

    private String description;
    
    private Set<QuestionnaireQuestionMinimalDto> questions;

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
    
    public Set<QuestionnaireQuestionMinimalDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionnaireQuestionMinimalDto> questions) {
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