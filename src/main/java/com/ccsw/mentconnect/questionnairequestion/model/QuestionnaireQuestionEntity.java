package com.ccsw.mentconnect.questionnairequestion.model;

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
import com.ccsw.mentconnect.question.model.QuestionEntity;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;

@Entity
@Table(name = "questionnaire_question")
public class QuestionnaireQuestionEntity {

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
    
    @ElementCollection
    @CollectionTable(name = "questionnairequestion_weekdays",
                     joinColumns = @JoinColumn(name = "questionnaire_question_id", referencedColumnName = "questionnaire_id"))
    @Column(name = "week_day")
    private List<Integer> weekDays;



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

    public List<Integer> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<Integer> weekDays) {
        this.weekDays = weekDays;
    }
    
    
    
    
}
