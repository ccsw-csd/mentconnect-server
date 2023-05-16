package com.ccsw.mentconnect.questionnairequestion.dto;

import java.io.Serializable;
import java.util.List;
import com.ccsw.mentconnect.answertypevalue.dto.AnswerTypeValueDto;
import com.ccsw.mentconnect.question.dto.QuestionDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;
import com.ccsw.mentconnect.questionnairequestion.model.TimeSlotEnum;
import com.ccsw.mentconnect.weekday.dto.WeekDayDto;

public class QuestionnaireQuestionDto implements Serializable {

    private Long id;

    private QuestionnaireDto questionnaire;

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

    public QuestionnaireDto getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(QuestionnaireDto questionnaire) {
        this.questionnaire = questionnaire;
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