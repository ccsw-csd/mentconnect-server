package com.ccsw.mentconnect.questionnairequestion.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;
import com.ccsw.mentconnect.answertypevalue.model.AnswerTypeValueEntity;
import com.ccsw.mentconnect.question.model.QuestionEntity;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.weekday.model.WeekDayEntity;

@Entity
@Table(name = "questionnaire_question")
public class QuestionnaireQuestionEntity{

    public static final String ATT_ID = "id";
    public static final String ATT_QUESTIONNAIRE = "questionnaire";
    public static final String ATT_QUESTION = "question";
    public static final String ATT_TIMESLOT = "timeslot";
    public static final String ATT_DAYWEEK = "dayweek";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id")
    private QuestionnaireEntity questionnaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "timeslot", nullable = false)
    private TimeSlotEnum timeslot;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "alert_config_answer_type_id", nullable = true)
    private AnswerTypeValueEntity alertConfigAnswerType;
    
    @Column(name = "alert_config_consecutive_answers", nullable = true)
    private Integer alertConfigConsecutiveAnswers;
    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(name = "questionnaire_question_weekday",
               joinColumns = @JoinColumn(name = "questionnaire_question_id"),
               inverseJoinColumns = @JoinColumn(name = "week_day"))
    private List<WeekDayEntity> weekDays;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionnaireEntity getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(QuestionnaireEntity questionnaire) {
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

    public List<WeekDayEntity> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<WeekDayEntity> weekDays) {
        this.weekDays = weekDays;
    }

    public AnswerTypeValueEntity getAlertConfigAnswerType() {
        return alertConfigAnswerType;
    }

    public void setAlertConfigAnswerType(AnswerTypeValueEntity alertConfigAnswerType) {
        this.alertConfigAnswerType = alertConfigAnswerType;
    }

    public int getAlertConfigConsecutiveAnswers() {
        return alertConfigConsecutiveAnswers;
    }

    public void setAlertConfigConsecutiveAnswers(Integer alertConfigConsecutiveAnswers) {
        this.alertConfigConsecutiveAnswers = alertConfigConsecutiveAnswers;
    }
    
    
    
    
}