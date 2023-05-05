package com.ccsw.mentconnect.questionnaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireInfoDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSearchDto;
import com.ccsw.mentconnect.questionnaire.logic.QuestionnaireService;

@RequestMapping(value = "/questionnaire")
@RestController
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<QuestionnaireDto> findAll() {

        return this.beanMapper.mapList(questionnaireService.findAll(), QuestionnaireDto.class);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/findPage", method = RequestMethod.POST)
    public Page<QuestionnaireDto> findPage(@RequestBody QuestionnaireSearchDto dto) {
        return this.beanMapper.mapPage(questionnaireService.findPage(dto), QuestionnaireDto.class);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public QuestionnaireDto saveQuestionnaire(@RequestBody QuestionnaireInfoDto questionnaireDto) throws AlreadyExistsException {

        return this.beanMapper.map(questionnaireService.saveQuestionnaire(questionnaireDto), QuestionnaireDto.class);
    }

}
