package com.ccsw.mentconnect.questionnaire.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.mentconnect.user.model.UserEntity;

@Entity
@Table(name = "questionnaire")
public class QuestionnaireEntity {

    public static final String ATT_ID = "id";
    public static final String ATT_DESCRIPTION = "description";
    public static final String ATT_QUESTIONS_NUMBER = "questionsNumber";
    public static final String ATT_PATIENTS_NUMBER = "patientsNumber";
    public static final String ATT_USER = "user.name";
    public static final String ATT_CREATE_DATE = "createDate";
    public static final String ATT_LAST_EDIT_DATE = "lastEditDate";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    // TO-DO Modificar cuanto este listo Preguntas
    @Column(name = "questions", nullable = false)
    private Integer questionsNumber;

    // TO-DO Modificar cuanto este listo Pacientes
    @Column(name = "patients", nullable = false)
    private Integer patientsNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "last_edit_date", nullable = false)
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
