package com.ccsw.mentconnect.questionnairepatient.dto;

import com.ccsw.mentconnect.questionnaire.dto.QuestionnairePlainDto;

import java.time.LocalDate;

public class QuestionnairePatientMinimalDto {

    private Long id;

    private QuestionnairePlainDto questionnaire;

    private LocalDate startDate;

    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public QuestionnairePlainDto getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(QuestionnairePlainDto questionnaire) {
        this.questionnaire = questionnaire;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
