package com.ccsw.mentconnect.diary.dto;

import java.time.LocalDate;
import com.ccsw.mentconnect.patient.model.PatientEntity;

public class DiaryDto {
    
    private Long id;

    private String description;

    private LocalDate createDate;

    private PatientEntity patient;

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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }
    
}
