package com.ccsw.mentconnect.diary.dto;

import java.util.Date;

public class DateSearchDiaryDto {
    
    private Long patientId;
    
    private Date startDate;

    private Date endDate;
    

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}