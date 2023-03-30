package com.ccsw.mentconnect.patient.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.user.model.UserEntity;

@Entity
@Table(name = "patient")
public class PatientEntity {
	
	public static final String ATT_ID = "id";
    public static final String ATT_USER = "user";
    public static final String ATT_NIF = "nif";
    public static final String ATT_GENDER = "gender";
    public static final String ATT_DATE = "dateBirth";
    public static final String ATT_PHONE = "phone";
    public static final String ATT_SIP = "sip";
    public static final String ATT_MEDICAL = "medicalHistory";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "nif", nullable = false)
    private String nif;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "sip")
    private String sip;

    @Column(name = "medical_history")
    private String medicalHistory;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

}
