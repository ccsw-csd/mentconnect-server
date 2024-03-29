package com.ccsw.mentconnect.question.model;

import javax.persistence.*;

import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;

@Entity
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_type_id")
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
