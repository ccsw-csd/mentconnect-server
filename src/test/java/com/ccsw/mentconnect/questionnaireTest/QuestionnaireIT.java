package com.ccsw.mentconnect.questionnaireTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSearchDto;
import com.ccsw.mentconnect.user.dto.UserDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class QuestionnaireIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/questionnaire/";

    public static final int TOTAL_QUESTIONNAIRE = 2;

    public static final String EXISTS_DESCRIPTION = "staff";

    public static final String NOT_EXISTS_DESCRIPTION = "x";

    public static final Long EXISTS_USER_ID = 1L;

    public static final Long NOT_EXISTS_USER_ID = 0L;

    ParameterizedTypeReference<List<QuestionnaireDto>> responseTypeList = new ParameterizedTypeReference<List<QuestionnaireDto>>() {
    };

    ParameterizedTypeReference<Page<QuestionnaireDto>> responseTypePage = new ParameterizedTypeReference<Page<QuestionnaireDto>>() {
    };

    @Test
    public void findAllShouldReturnAllQuestionnaire() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_QUESTIONNAIRE, response.getBody().size());

    }

    @Test
    public void findPageShouldReturnPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_QUESTIONNAIRE, response.getBody().getContent().size());
    }

    @Test
    public void findPageWithNullValueShouldReturnPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(null);
        dto.setQuestionsNumber(null);
        dto.setPatientsNumber(null);
        dto.setUser(null);

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_QUESTIONNAIRE, response.getBody().getTotalElements());
    }

    @Test
    public void findPageWithExistsDescriptionValueShouldReturnPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(EXISTS_DESCRIPTION);
        dto.setQuestionsNumber(null);
        dto.setPatientsNumber(null);
        dto.setUser(null);

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    public void findPageWithNotExistsDescriptionValueShouldReturnPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(NOT_EXISTS_DESCRIPTION);
        dto.setQuestionsNumber(null);
        dto.setPatientsNumber(null);
        dto.setUser(null);

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getTotalElements());
    }

    @Test
    public void findPageWithExistsUserShouldReturnPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(null);
        dto.setQuestionsNumber(null);
        dto.setPatientsNumber(null);

        UserDto user = new UserDto();
        user.setId(EXISTS_USER_ID);

        dto.setUser(user);

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(2, response.getBody().getTotalElements());
    }

    @Test
    public void findPageWithNotExistsUserShouldReturnPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(null);
        dto.setQuestionsNumber(null);
        dto.setPatientsNumber(null);

        UserDto user = new UserDto();
        user.setId(NOT_EXISTS_USER_ID);

        dto.setUser(user);

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getTotalElements());
    }

}
