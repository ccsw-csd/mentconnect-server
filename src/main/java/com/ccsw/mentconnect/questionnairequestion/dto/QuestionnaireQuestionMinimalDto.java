package com.ccsw.mentconnect.questionnairequestion.dto;

import com.ccsw.mentconnect.question.dto.QuestionMinimalDto;

import java.io.Serializable;

public class QuestionnaireQuestionMinimalDto implements Serializable {

    private Long id;

    private QuestionMinimalDto question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public QuestionMinimalDto getQuestion() {
        return question;
    }

    public void setQuestion(QuestionMinimalDto question) {
        this.question = question;
    }

}