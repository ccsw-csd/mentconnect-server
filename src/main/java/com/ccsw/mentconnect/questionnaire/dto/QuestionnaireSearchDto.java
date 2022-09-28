package com.ccsw.mentconnect.questionnaire.dto;

import org.springframework.data.domain.Pageable;

import com.ccsw.mentconnect.user.dto.UserDto;

public class QuestionnaireSearchDto {

    private Long id;

    private String description;

    private UserDto user;

    private Pageable pageable;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

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

}
