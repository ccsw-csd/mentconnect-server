package com.ccsw.mentconnect.questionnaire.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ccsw.mentconnect.question.model.QuestionEntity;
import com.ccsw.mentconnect.user.model.UserEntity;

@Entity
@Table(name = "questionnaire")
public class QuestionnaireEntity {

    public static final String ATT_ID = "id";
    public static final String ATT_DESCRIPTION = "description";
    public static final String ATT_QUESTIONS_NUMBER = "questionsNumber";
    public static final String ATT_PATIENTS_NUMBER = "patientsNumber";
    public static final String ATT_USER = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "questionnaire_question", joinColumns = @JoinColumn(name = "questionnaire_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    // @Formula(value = "(SELECT COUNT(q.questionnaire_id) FROM
    // questionnaire_question q WHERE q.questionnaire_id = id GROUP BY
    // q.questionnaire_id)")
    public List<QuestionEntity> questions;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "questionnaire_patient", joinColumns = @JoinColumn(name = "questionnaire_id"), inverseJoinColumns = @JoinColumn(name = "patient_id"))
    // @Formula(value = "(SELECT COUNT(q.questionnaire_id) FROM
    // questionnaire_patient q WHERE q.questionnaire_id = id GROUP BY
    // q.questionnaire_id)")
    public List<QuestionEntity> patients;

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

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public List<QuestionEntity> getPatients() {
        return patients;
    }

    public void setPatients(List<QuestionEntity> patients) {
        this.patients = patients;
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
