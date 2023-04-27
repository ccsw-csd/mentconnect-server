package com.ccsw.mentconnect.answertypevalue.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.answertype.dto.AnswerTypeDto;
import com.ccsw.mentconnect.answertype.logic.AnswerTypeService;
import com.ccsw.mentconnect.answertype.model.AnswerTypeEntity;
import com.ccsw.mentconnect.answertypevalue.dto.AnswerTypeValueDto;
import com.ccsw.mentconnect.answertypevalue.logic.AnswerTypeValueService;
import com.ccsw.mentconnect.answertypevalue.model.AnswerTypeValueEntity;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;

@RequestMapping(value = "/answer-type-value")
@RestController
public class AnswerTypeValueController {

    @Autowired
    private AnswerTypeValueService answerTypeValueService;
    
    @Autowired
    private AnswerTypeService answerTypeService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<AnswerTypeValueDto> findAll() {

        return this.beanMapper.mapList(answerTypeValueService.findAll(), AnswerTypeValueDto.class);
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/findByAnswerType", method = RequestMethod.POST)
    public List<AnswerTypeValueDto> findByAnswerType(@RequestBody AnswerTypeDto answerTypeDto) {
        AnswerTypeEntity answerTypeEntity = beanMapper.map(answerTypeDto, AnswerTypeEntity.class);
        return beanMapper.mapList(answerTypeValueService.findByAnswerType(answerTypeEntity), AnswerTypeValueDto.class);
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/findByAnswerTypeDescription", method = RequestMethod.GET)
    public List<AnswerTypeValueDto> findByAnswerTypeDescription(@RequestParam String description) {
        List<AnswerTypeValueEntity> answerTypeEntities = answerTypeValueService.findByAnswerTypeDescription(description);
        return beanMapper.mapList(answerTypeEntities, AnswerTypeValueDto.class);
    }
    
}
