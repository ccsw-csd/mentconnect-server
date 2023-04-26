package com.ccsw.mentconnect.questionnairequestionweekday.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.ccsw.mentconnect.questionnairequestion.model.QuestionnaireQuestionEntity;
@Entity
@Table(name = "questionnairequestion_weekdays")
public class QuestionnaireQuestionWeekDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_question_id",referencedColumnName = "question_id")
    private QuestionnaireQuestionEntity questionnaireQuestion;

    @Column(name = "week_day")
    private Integer weekDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionnaireQuestionEntity getQuestionnaireQuestion() {
        return questionnaireQuestion;
    }
    

    public void setQuestionnaireQuestion(QuestionnaireQuestionEntity questionnaireQuestion) {
        this.questionnaireQuestion = questionnaireQuestion;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }
}
