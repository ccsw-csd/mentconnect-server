package com.ccsw.mentconnect.answertypevalue.dto;

import com.ccsw.mentconnect.answertype.dto.AnswerTypeDto;

public class AnswerTypeValueDto {

    private Long id;

    private String value;

    private AnswerTypeDto answerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AnswerTypeDto getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerTypeDto answerType) {
        this.answerType = answerType;
    }
}
