package com.ccsw.mentconnect.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.question.dto.QuestionDto;
import com.ccsw.mentconnect.question.logic.QuestionService;

@RequestMapping(value = "question")
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<QuestionDto> findAll() {

        return this.beanMapper.mapList(questionService.findAll(), QuestionDto.class);
    }

}