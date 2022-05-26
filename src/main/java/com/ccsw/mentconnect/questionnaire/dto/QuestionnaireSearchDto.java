package com.ccsw.mentconnect.questionnaire.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;

import com.ccsw.mentconnect.user.model.UserEntity;

public class QuestionnaireSearchDto {

    private Long id;

    private String description;

    // TO-DO Modificar cuanto este listo Preguntas
    private Integer questionsNumber;
    // TO-DO Modificar cuanto este listo Pacientes
    private Integer patientsNumber;

    private UserEntity user;

    private LocalDate createDate;

    private LocalDate lastEditDate;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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
