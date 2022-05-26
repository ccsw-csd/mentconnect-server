package com.ccsw.mentconnect.questionnaire.dto;

import com.ccsw.mentconnect.user.dto.UserDto;
import org.springframework.data.domain.Pageable;

public class QuestionnaireSearchDto {

    private Long id;

    private String description;

    private Integer questionsNumber;

    private Integer patientsNumber;

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

    public Integer getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(Integer questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public Integer getPatientsNumber() {
        return patientsNumber;
    }

    public void setPatientsNumber(Integer patientsNumber) {
        this.patientsNumber = patientsNumber;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

}
