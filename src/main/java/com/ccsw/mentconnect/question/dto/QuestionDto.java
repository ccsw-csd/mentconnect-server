package com.ccsw.mentconnect.question.dto;

import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;

public class QuestionDto {

    private Long id;

    private String question;

    private AnswerTypeEntity answerType;

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

    public AnswerTypeEntity getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerTypeEntity answerType) {
        this.answerType = answerType;
    }
}
