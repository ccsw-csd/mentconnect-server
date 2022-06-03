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
import com.ccsw.mentconnect.patient.dto.PatientDtoFull;
import com.ccsw.mentconnect.user.dto.UserFullDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PatientIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/patient/";

    public static final String NOT_EXISTS_USERNAME_USER = "jopepe";
    public static final String EXISTS_USERNAME_USER = "admin";

    public static final String EXISTS_USER_NIF = "12345678Y";
    public static final String NOT_EXISTS_USER_NIF = "12345678B";

    private PatientDtoFull patientDtoFull;
    private UserFullDto userFullDto;

    @BeforeEach
    public void setUp() {
        patientDtoFull = new PatientDtoFull();
        userFullDto = new UserFullDto();

        this.userFullDto.setName("Admin");
        this.userFullDto.setSurnames("Admin");
        this.userFullDto.setEmail("admin@meentconnect.com");
        this.patientDtoFull.setUser(userFullDto);
        this.patientDtoFull.setNif("12345678P");
        this.patientDtoFull.setGender("H");
        this.patientDtoFull.setPhone("123456789");
    }

    @Test
    public void saveWithExistsUsernameAndNotExistNifShouldThrowException() {

        patientDtoFull.getUser().setUsername(EXISTS_USERNAME_USER);
        patientDtoFull.setNif(NOT_EXISTS_USER_NIF);
        HttpEntity<?> httpEntity = new HttpEntity<>(patientDtoFull, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, httpEntity,
                PatientDtoFull.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void saveWithNotExistsUsernameAndExistsNifShouldThrowException() {

        patientDtoFull.getUser().setUsername(NOT_EXISTS_USERNAME_USER);
        patientDtoFull.setNif(EXISTS_USER_NIF);
        HttpEntity<?> httpEntity = new HttpEntity<>(patientDtoFull, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, httpEntity,
                PatientDtoFull.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void saveWithNotExistsUsernameAndNifShouldCreateNewPatient() {

        patientDtoFull.getUser().setUsername(NOT_EXISTS_USERNAME_USER);
        patientDtoFull.setNif(NOT_EXISTS_USER_NIF);
        HttpEntity<?> httpEntity = new HttpEntity<>(patientDtoFull, getHeaders());

        ResponseEntity<PatientDtoFull> responseSave = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, httpEntity, PatientDtoFull.class);

        assertEquals(patientDtoFull.getUser().getUsername(), responseSave.getBody().getUser().getUsername());

        ResponseEntity<PatientDtoFull> responseGetPatient = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + responseSave.getBody().getId(), HttpMethod.GET,
                new HttpEntity<>(getHeaders()), PatientDtoFull.class);

        assertEquals(responseGetPatient.getBody().getUser().getId(), responseSave.getBody().getUser().getId());
    }

}
