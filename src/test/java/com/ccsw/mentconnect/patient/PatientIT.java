package com.ccsw.mentconnect.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.patient.dto.PatientDto;
import com.ccsw.mentconnect.user.dto.UserFullDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PatientIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/patient/";

    public static final String NOT_EXISTS_USERNAME_USER = "jopepe";
    public static final String EXISTS_USERNAME_USER = "admin";

    private PatientDto patientDto;
    private UserFullDto userFullDto;

    @BeforeEach
    public void setUp() {
        patientDto = new PatientDto();
        userFullDto = new UserFullDto();

        this.userFullDto.setName("Admin");
        this.userFullDto.setSurnames("Admin");
        this.userFullDto.setEmail("admin@meentconnect.com");
        this.patientDto.setUser(userFullDto);
        this.patientDto.setNif("12345678P");
        this.patientDto.setGender("H");
        this.patientDto.setPhone("123456789");
    }

    @Test
    public void saveWithExistsUsernameShouldThrowException() {

        patientDto.getUser().setUsername(EXISTS_USERNAME_USER);
        HttpEntity<?> httpEntity = new HttpEntity<>(patientDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, httpEntity,
                PatientDto.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void saveWithNotExistsUsernameShouldCreateNewPatient() {

        patientDto.getUser().setUsername(NOT_EXISTS_USERNAME_USER);
        HttpEntity<?> httpEntity = new HttpEntity<>(patientDto, getHeaders());

        ResponseEntity<PatientDto> responseSave = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, httpEntity, PatientDto.class);

        assertEquals(patientDto.getUser().getUsername(), responseSave.getBody().getUser().getUsername());

        ResponseEntity<PatientDto> responseGetPatient = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + responseSave.getBody().getId(), HttpMethod.GET,
                new HttpEntity<>(getHeaders()), PatientDto.class);

        assertEquals(responseGetPatient.getBody().getUser().getId(), responseSave.getBody().getUser().getId());
    }

}
