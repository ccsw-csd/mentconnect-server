package com.ccsw.mentconnect.questionnaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;
import com.ccsw.mentconnect.questionnaire.logic.QuestionnaireService;

import ma.glasnost.orika.MapperFacade;

@RequestMapping(value = "/questionnaire")
@RestController
public class QuestionnaireController {
	
	@Autowired
	private QuestionnaireService questService;
	
	@Autowired
	private MapperFacade mapperFacade;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(path = "/findAll", method = RequestMethod.GET)
	public List<QuestionnaireDto> findAll(){
		
		return this.mapperFacade.mapAsList(questService.findAll(), QuestionnaireDto.class);
	}
	
}
