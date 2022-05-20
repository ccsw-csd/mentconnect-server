package com.ccsw.mentconnect.questionary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.questionary.dto.QuestionnaireDto;
import com.ccsw.mentconnect.questionary.logic.QuestionnaireService;

import ma.glasnost.orika.MapperFacade;

@RequestMapping(value = "/questionnaire")
@RestController
public class QuestionnaireController {
	
	@Autowired
	private QuestionnaireService questService;
	
	@Autowired
	private MapperFacade mapperFacade;
	
	
	@RequestMapping(path = "/findAll", method = RequestMethod.GET)
	public List<QuestionnaireDto> findAll(){
		
		return this.mapperFacade.mapAsList(questService.findAll(), QuestionnaireDto.class);
	}
	
}
