package com.ccsw.mentconnect.questionnaireTest;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class QuestionnaireIT extends BaseITAbstract{
	
	public static final String SERVICE_PATH = "/questionnaire/";
	
	public static final int TOTAL_QUESTIONNAIRE = 2;
	
	ParameterizedTypeReference<List<QuestionnaireDto>> responseTypeList = new ParameterizedTypeReference<List<QuestionnaireDto>>(){};

	@Test
	public void findAllShouldReturnAllQuestionnaire() {
		
		HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
		
		ResponseEntity<List<QuestionnaireDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeList);
	
		assertNotNull(response);
		assertEquals(TOTAL_QUESTIONNAIRE, response.getBody().size());
	
	}
}
