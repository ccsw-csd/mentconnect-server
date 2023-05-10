package com.ccsw.mentconnect.questionnairequestion.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ccsw.mentconnect.answertypevalue.dto.AnswerTypeValueDto;
import com.ccsw.mentconnect.answertypevalue.model.AnswerTypeValueEntity;
import com.ccsw.mentconnect.question.dto.QuestionDto;
import com.ccsw.mentconnect.question.model.QuestionEntity;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;
import com.ccsw.mentconnect.questionnairequestion.model.TimeSlotEnum;
import com.ccsw.mentconnect.weekday.dto.WeekDayDto;
import com.ccsw.mentconnect.weekday.model.WeekDayEntity;

public class QuestionnaireQuestionResponseDto implements Serializable {

    private Long id;

    private QuestionDto question;

    private TimeSlotEnum timeslot;
    
    private List<WeekDayDto> weekDays;
    
    private AnswerTypeValueDto alertConfigAnswerType;
    
    private Integer alertConfigConsecutiveAnswers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public QuestionDto getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDto question) {
        this.question = question;
    }

    public TimeSlotEnum getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlotEnum timeslot) {
        this.timeslot = timeslot;
    }

    public List<WeekDayDto> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<WeekDayDto> weekDays) {
        this.weekDays = weekDays;
    }

    public AnswerTypeValueDto getAlertConfigAnswerType() {
        return alertConfigAnswerType;
    }

    public void setAlertConfigAnswerType(AnswerTypeValueDto alertConfigAnswerType) {
        this.alertConfigAnswerType = alertConfigAnswerType;
    }

    public Integer getAlertConfigConsecutiveAnswers() {
        return alertConfigConsecutiveAnswers;
    }

    public void setAlertConfigConsecutiveAnswers(Integer alertConfigConsecutiveAnswers) {
        this.alertConfigConsecutiveAnswers = alertConfigConsecutiveAnswers;
    }

    

}