package com.ccsw.mentconnect.weekday.dto;

import java.io.Serializable;

public class WeekDayDto implements Serializable {

    private int id;

    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
    
}