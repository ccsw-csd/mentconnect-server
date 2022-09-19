package com.ccsw.mentconnect.questionnaire;

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

    public static final String EXISTS_DESCRIPTION = "Prueba de descripcion admin ";

    public static final String NOT_EXISTS_DESCRIPTION = "x";

    public static final Long EXISTS_USER_ID = 1L;

    public static final Long NOT_EXISTS_USER_ID = 0L;

    private static final Integer NOT_EXISTS_QUESTIONS_NUMBER = 20;
    private static final Integer EXISTS_QUESTIONS_NUMBER = 2;

    private static final Integer NOT_EXISTS_PATIENTS_NUMBER = 20;
    private static final Integer EXISTS_PATIENTS_NUMBER = 1;

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
        assertEquals(1, response.getBody().getTotalElements());
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

    @Test
    public void findPageWithExistsQuestionsNumberValueShouldReturnPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(null);
        dto.setQuestionsNumber(EXISTS_QUESTIONS_NUMBER);
        dto.setPatientsNumber(null);
        dto.setUser(null);

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    public void findPageWithNotExistsQuestionsNumberValueShouldReturnPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(null);
        dto.setQuestionsNumber(NOT_EXISTS_QUESTIONS_NUMBER);
        dto.setPatientsNumber(null);
        dto.setUser(null);

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getTotalElements());
    }

    @Test
    public void findPageWithExistsPatientsNumberValueShouldReturnPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(null);
        dto.setQuestionsNumber(null);
        dto.setPatientsNumber(EXISTS_PATIENTS_NUMBER);
        dto.setUser(null);

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    public void findPageWithNotExistsPatientsNumberValueShouldReturnEmptyPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(null);
        dto.setQuestionsNumber(null);
        dto.setPatientsNumber(NOT_EXISTS_PATIENTS_NUMBER);
        dto.setUser(null);

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getTotalElements());
    }

    @Test
    public void findPageWithAllExistsFiltersShouldReturnPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(EXISTS_DESCRIPTION);
        dto.setQuestionsNumber(EXISTS_QUESTIONS_NUMBER);
        dto.setPatientsNumber(EXISTS_PATIENTS_NUMBER);

        UserDto user = new UserDto();
        user.setId(EXISTS_USER_ID);

        dto.setUser(user);

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    public void findPageWithAllNotExistsFiltersShouldReturnEmptyPageQuestionnaire() {

        QuestionnaireSearchDto dto = new QuestionnaireSearchDto();

        dto.setPageable(PageRequest.of(0, 10));
        dto.setDescription(NOT_EXISTS_DESCRIPTION);
        dto.setQuestionsNumber(NOT_EXISTS_QUESTIONS_NUMBER);
        dto.setPatientsNumber(NOT_EXISTS_PATIENTS_NUMBER);

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
