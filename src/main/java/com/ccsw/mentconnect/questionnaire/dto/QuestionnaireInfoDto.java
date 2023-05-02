package com.ccsw.mentconnect.questionnaire.dto;

import java.time.LocalDate;
import java.util.List;
import com.ccsw.mentconnect.question.dto.QuestionDto;
import com.ccsw.mentconnect.user.dto.UserDto;

public class QuestionnaireInfoDto {

    private Long id;

    private String description;

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