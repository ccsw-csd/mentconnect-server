package com.ccsw.mentconnect.patient.dto;

import java.time.LocalDate;

import com.ccsw.mentconnect.user.dto.UserFullDto;

public class PatientDtoFull {

    private Long id;

    private UserFullDto user;

    private String nif;

    private String gender;

    private LocalDate dateBirth;

    private String phone;

    private String sip;

    private String medicalHistory;

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
