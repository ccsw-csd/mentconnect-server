package com.ccsw.mentconnect.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.patient.dto.PatientDto;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;
import com.ccsw.mentconnect.patient.dto.PatientSearchDto;
import com.ccsw.mentconnect.user.dto.UserDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PatientIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/patient/";

    public static final String NOT_EXISTS_USERNAME_PATIENT = "pepep";
    public static final String EXISTS_USERNAME_PATIENT = "admin";

    public static final String EXISTS_PATIENT_NIF = "12345678Y";
    public static final String NOT_EXISTS_PATIENT_NIF = "12345678B";

    public static final String NIF = "12345678A";
    public static final String NAME = "Admin";
    public static final String SURNAME = "Admin";
    public static final String EMAIL = "admin@meentconnect.com";

    public static final Integer TOTAL_PATIENT = 3;
    public static final Integer SEARCH_PATIENT = 1;
    public static final Integer EMPTY_PATIENT = 0;

    private PatientDto patientDto;
    private UserDto userDto;
    private PatientSearchDto patientSearchDto;

    ParameterizedTypeReference<List<PatientDto>> responseTypeList = new ParameterizedTypeReference<List<PatientDto>>() {
    };

    ParameterizedTypeReference<Page<PatientDto>> responseTypePage = new ParameterizedTypeReference<Page<PatientDto>>() {
    };

    @BeforeEach
    public void setUp() {
        patientDto = new PatientDto();
        userDto = new UserDto();
        patientSearchDto = new PatientSearchDto();

        this.userDto.setName("Admin");
        this.userDto.setSurnames("Admin");
        this.userDto.setEmail("admin@meentconnect.com");
        this.patientDto.setUser(userDto);
        this.patientDto.setNif("12345678P");
        this.patientDto.setGender("H");
        this.patientDto.setPhone("123456789");

        this.userDto.setName("");
        this.userDto.setSurnames("");
        this.userDto.setEmail("");
        this.patientSearchDto.setUser(userDto);
        this.patientSearchDto.setNif("");
        this.patientSearchDto.setGender("");
        this.patientSearchDto.setPhone("");
    }

    @Test
    public void saveWithExistsUsernameAndNotExistNifShouldThrowException() {
        patientDto.getUser().setUsername(EXISTS_USERNAME_PATIENT);
        patientDto.setNif(NOT_EXISTS_PATIENT_NIF);
        HttpEntity<?> httpEntity = new HttpEntity<>(patientDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, httpEntity, PatientFullDto.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void saveWithNotExistsUsernameAndExistsNifShouldThrowException() {
        patientDto.getUser().setUsername(NOT_EXISTS_USERNAME_PATIENT);
        patientDto.setNif(EXISTS_PATIENT_NIF);
        HttpEntity<?> httpEntity = new HttpEntity<>(patientDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, httpEntity, PatientFullDto.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void saveWithNotExistsUsernameAndNifShouldCreateNewPatient() {
        patientDto.getUser().setUsername(NOT_EXISTS_USERNAME_PATIENT);
        patientDto.setNif(NOT_EXISTS_PATIENT_NIF);
        HttpEntity<?> httpEntity = new HttpEntity<>(patientDto, getHeaders());

        ResponseEntity<PatientFullDto> responseSave = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, httpEntity, PatientFullDto.class);

        assertEquals(patientDto.getUser().getUsername(), responseSave.getBody().getUser().getUsername());

        ResponseEntity<PatientFullDto> responseGetPatient = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + responseSave.getBody().getId(), HttpMethod.GET, new HttpEntity<>(getHeaders()), PatientFullDto.class);

        assertEquals(responseGetPatient.getBody().getUser().getId(), responseSave.getBody().getUser().getId());
    }

    @Test
    public void findPageShouldReturnPagePatient() {
        patientSearchDto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(patientSearchDto, getHeaders());

        ResponseEntity<Page<PatientDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PATIENT, response.getBody().getContent().size());
    }

    @Test
    public void findPageNotExistsPatientShouldReturnEmpty() {
        patientSearchDto.setNif(NOT_EXISTS_PATIENT_NIF);

        patientSearchDto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(patientSearchDto, getHeaders());

        ResponseEntity<Page<PatientDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(EMPTY_PATIENT, response.getBody().getContent().size());
    }

    @Test
    public void findPageExistsNameAndUsernameShouldReturnPatients() {
        patientDto.getUser().setName(NAME);
        patientDto.getUser().setUsername(EXISTS_USERNAME_PATIENT);

        patientSearchDto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(patientSearchDto, getHeaders());

        ResponseEntity<Page<PatientDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(SEARCH_PATIENT, response.getBody().getContent().size());
    }

    @Test
    public void findPageExistsNifShouldReturnPatient() {
        patientSearchDto.setNif(EXISTS_PATIENT_NIF);
        patientSearchDto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(patientSearchDto, getHeaders());

        ResponseEntity<Page<PatientDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(SEARCH_PATIENT, response.getBody().getContent().size());
    }

    @Test
    public void findAllShouldReturnAllPatient() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<PatientDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_PATIENT, response.getBody().size());
    }

    @Test
    public void findExistsNameShouldReturnPatientFilter() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
        ResponseEntity<List<PatientDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findFilter/" + NAME, HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(SEARCH_PATIENT, response.getBody().size());
    }
}
