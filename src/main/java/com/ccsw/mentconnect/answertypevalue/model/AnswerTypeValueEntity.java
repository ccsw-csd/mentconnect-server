package com.ccsw.mentconnect.answertypevalue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;

@Entity
@Table(name = "answer_type_value")
public class AnswerTypeValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "answer_type_id")
    private AnswerTypeEntity answerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AnswerTypeEntity getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerTypeEntity answerType) {
        this.answerType = answerType;
    }

}
