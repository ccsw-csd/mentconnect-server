package com.ccsw.mentconnect.questionnaire.logic;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSimpleDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSearchDto;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;

public interface QuestionnaireService {

    QuestionnaireEntity getQuestionnaire(Long id) throws EntityNotFoundException;

    List<QuestionnaireEntity> findAll();

    Page<QuestionnaireEntity> findPage(QuestionnaireSearchDto dto);

    QuestionnaireEntity saveOrUpdateQuestionnaire(QuestionnaireSimpleDto questionnaireDto) throws AlreadyExistsException, EntityNotFoundException;


    

}
