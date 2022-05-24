package com.ccsw.mentconnect.questionnaire.dto;

import org.springframework.data.domain.Pageable;

public class QuestionnaireSearchDto {

    private Pageable pageable;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

}
