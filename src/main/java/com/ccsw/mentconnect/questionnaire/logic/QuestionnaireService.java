package com.ccsw.mentconnect.questionnaire.logic;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireInfoDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSearchDto;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;

public interface QuestionnaireService {

    List<QuestionnaireEntity> findAll();

    Page<QuestionnaireEntity> findPage(QuestionnaireSearchDto dto);

    QuestionnaireEntity saveQuestionnaire(QuestionnaireInfoDto questionnaireDto) throws AlreadyExistsException;
    

}
