package com.ccsw.mentconnect.patient.dto;

import java.sql.Date;

import com.ccsw.mentconnect.user.dto.UserFullDto;

public class PatientDto {

    private Long id;

    private UserFullDto user;

    private String nif;

    private Character gender;

    private Date date_birth;

    private String phone;

    private String sip;

    private String medical_history;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserFullDto getUser() {
        return user;
    }

    public void setUser(UserFullDto user) {
        this.user = user;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
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

    public String getMedical_history() {
        return medical_history;
    }

    public void setMedical_history(String medical_history) {
        this.medical_history = medical_history;
    }

}
