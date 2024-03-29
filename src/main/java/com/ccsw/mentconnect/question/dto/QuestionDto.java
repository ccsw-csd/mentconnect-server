package com.ccsw.mentconnect.question.dto;

import com.ccsw.mentconnect.answertype.dto.AnswerTypeDto;

public class QuestionDto {

    private Long id;

    private String question;

    private AnswerTypeDto answerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public AnswerTypeDto getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerTypeDto answerType) {
        this.answerType = answerType;
    }
}
