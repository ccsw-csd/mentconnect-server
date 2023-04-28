package com.ccsw.mentconnect.questionnairequestion.dto;

import java.util.List;
import com.ccsw.mentconnect.question.model.QuestionEntity;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;
import com.ccsw.mentconnect.questionnairequestion.model.TimeSlotEnum;
import com.ccsw.mentconnect.questionnairequestionweekday.model.QuestionnaireQuestionWeekDayEntity;

public class QuestionnaireQuestionDto {

    private Long id;

    private QuestionnaireDto questionnaire;

    private QuestionEntity question;

    private TimeSlotEnum timeslot;
    
    private List<QuestionnaireQuestionWeekDayEntity> weekDays;

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

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public TimeSlotEnum getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlotEnum timeslot) {
        this.timeslot = timeslot;
    }

    public List<QuestionnaireQuestionWeekDayEntity> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<QuestionnaireQuestionWeekDayEntity> weekDays) {
        this.weekDays = weekDays;
    }

    

}
