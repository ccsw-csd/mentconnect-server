package com.ccsw.mentconnect.questionnaire.logic;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.mentconnect.patient.dto.PatientSearchDto;
import com.ccsw.mentconnect.patient.model.PatientEntity;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireAvailableDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSearchDto;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;

public interface QuestionnaireService {

    List<QuestionnaireEntity> findAll();

    Page<QuestionnaireEntity> findPage(QuestionnaireSearchDto dto);
    

}
