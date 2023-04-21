package com.ccsw.mentconnect.diary.dto;

import java.time.LocalDate;

public class DateSearchDiaryDto {
    
    private Long patientId;
    
    private LocalDate startDate;

    private LocalDate endDate;
    

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}