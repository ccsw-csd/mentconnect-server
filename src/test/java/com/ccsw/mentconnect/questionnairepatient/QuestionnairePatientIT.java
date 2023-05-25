package com.ccsw.mentconnect.questionnairepatient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.patient.dto.PatientDto;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
import com.ccsw.mentconnect.questionnairequestion.dto.QuestionnaireQuestionSimpleDto;
import com.ccsw.mentconnect.user.dto.UserDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class QuestionnairePatientIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/questionnaire-patient/";

    public static final int TOTAL_QUESTIONNAIRES_AVAILABLES = 1;
    
    public static final int TOTAL_QUESTIONNAIRES_ASSIGNES = 2;
    
    public static final int PATIENT_ID_EXISTS_AVAILABLE = 2;
    
    public static final int PATIENT_ID_EXISTS_ASSIGNED = 1;
    
    private UserDto userDto;
    private PatientDto patientDto;
    private QuestionnaireDto questionnaireDto;
    private List<QuestionnaireQuestionSimpleDto> questionsDto;
    private QuestionnairePatientDto questionnairePatientDto;

    ParameterizedTypeReference<List<QuestionnaireDto>> responseTypeList = new ParameterizedTypeReference<List<QuestionnaireDto>>() {
    };

    ParameterizedTypeReference<List<QuestionnairePatientDto>> responseTypeListQP = new ParameterizedTypeReference<List<QuestionnairePatientDto>>() {
    };
    
    @BeforeEach
    public void setUp() {
        questionnairePatientDto = new QuestionnairePatientDto();
        patientDto = new PatientDto();
        userDto = new UserDto();
        questionnaireDto = new QuestionnaireDto();
        questionsDto = new ArrayList<QuestionnaireQuestionSimpleDto>();

        this.userDto.setName("Admin");
        this.userDto.setSurnames("Admin");
        this.userDto.setEmail("admin@meentconnect.com");
        this.patientDto.setId(2L);
        this.patientDto.setUser(userDto);
        this.patientDto.setNif("12345678P");
        this.patientDto.setGender("H");
        this.patientDto.setPhone("123456789");
        this.questionnaireDto.setId(1L);
        this.questionnaireDto.setUser(userDto);
        this.questionnaireDto.setCreateDate(LocalDate.parse("2022-02-01"));
        this.questionnaireDto.setDescription("hola");
        this.questionnaireDto.setQuestions(questionsDto);
        this.questionnairePatientDto.setQuestionnaire(questionnaireDto);
        this.questionnairePatientDto.setPatient(patientDto);
        this.questionnairePatientDto.setStartDate(LocalDate.parse("2022-03-02"));
        this.questionnairePatientDto.setEndDate(LocalDate.parse("2023-03-05"));
    }
    
    @Test
    public void findAllShouldReturnAllQuestionnaireAvailables() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<QuestionnaireDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "questionnaire-available/"+PATIENT_ID_EXISTS_AVAILABLE, HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_QUESTIONNAIRES_AVAILABLES, response.getBody().size());

    }
    
    @Test
    public void findAllShouldReturnAllQuestionnaireAssigned() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<QuestionnairePatientDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + PATIENT_ID_EXISTS_ASSIGNED, HttpMethod.GET, httpEntity, responseTypeListQP);

        assertNotNull(response);
        assertEquals(TOTAL_QUESTIONNAIRES_ASSIGNES, response.getBody().size());

    }
    
    @Test
    public void saveWithNotExistsDateAssignedShouldAssignNewQuestionnaire() {
        HttpEntity<?> httpEntity = new HttpEntity<>(questionnairePatientDto, getHeaders());
        
        ResponseEntity<QuestionnairePatientDto> responseSave = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, httpEntity, QuestionnairePatientDto.class);
        
        assertEquals(questionnairePatientDto.getStartDate(), responseSave.getBody().getStartDate());
        
        assertEquals(questionnairePatientDto.getEndDate(), responseSave.getBody().getEndDate());
        
        /*ResponseEntity<QuestionnairePatientDto> responseGetQuestionnairePatient = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + responseSave.getBody().getPatient().getId(), HttpMethod.GET, new HttpEntity<>(getHeaders()), QuestionnairePatientDto.class);
        
        assertEquals(responseGetQuestionnairePatient.getBody().getId(), responseSave.getBody().getId());*/
    }
    
    @Test
    public void deleteQuestionnaireAssigned(){
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
        
        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + 3,
                HttpMethod.DELETE, httpEntity, QuestionnairePatientDto.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    }

