package com.ccsw.mentconnect.diary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.diary.dto.DateSearchDiaryDto;
import com.ccsw.mentconnect.diary.dto.DiaryDto;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;
import com.ccsw.mentconnect.user.dto.UserFullDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DiaryIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/diary/";

    public static final int TOTAL_DIARYS = 3;
    
    public static final int TOTAL_DIARYS_FILTERED = 1;
    
    public static final int EMPTY_DIARYS_FILTERED = 0;

    public static final Long EXISTS_PATIENT_ID = 1L;

    public static final Long NOT_EXISTS_PATIENT_ID = 0L;

    ParameterizedTypeReference<List<DiaryDto>> responseTypeList = new ParameterizedTypeReference<List<DiaryDto>>() {
    };
    
    ParameterizedTypeReference<List<DateSearchDiaryDto>> responseTypeListDates = new ParameterizedTypeReference<List<DateSearchDiaryDto>>() {
    };

    ParameterizedTypeReference<DiaryDto> responseType = new ParameterizedTypeReference<DiaryDto>() {
    };
    private PatientFullDto patientDto;
    private UserFullDto userDto;
    private DateSearchDiaryDto dateSearchDiaryDto = new DateSearchDiaryDto();
    private DiaryDto diaryDto = new DiaryDto();
    
    @BeforeEach
    public void setUp() {
        patientDto = new PatientFullDto();
        userDto = new UserFullDto();
        this.userDto.setUsername("adminvjkhkjhv");
        this.userDto.setName("Admin");
        this.userDto.setSurnames("Admin");
        this.userDto.setEmail("admin@meentconnect.com");
        
        this.patientDto.setUser(this.userDto);
        this.patientDto.setNif("12345678P");
        this.patientDto.setGender("H");
        this.patientDto.setPhone("123456789");
    }

    @Test
    public void getDiaryByPatientIdWithEmptyFiltersShouldReturnAllDiarysByPatientId() {
        dateSearchDiaryDto.setPatientId(EXISTS_PATIENT_ID);
        HttpEntity<?> httpEntity = new HttpEntity<>(dateSearchDiaryDto,getHeaders());

        ResponseEntity<List<DiaryDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "filter", HttpMethod.POST, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_DIARYS, response.getBody().size());

    }
    
    
    @Test
    public void getDiaryByPatientIdFilteredByDateExactShouldReturnAllDiarysByPatientIdFiltered() {
        dateSearchDiaryDto.setPatientId(EXISTS_PATIENT_ID);
        
        LocalDate localDate = LocalDate.of(2023, 3, 10);
        
        dateSearchDiaryDto.setStartDate(localDate);
        dateSearchDiaryDto.setEndDate(localDate);
        
        HttpEntity<?> httpEntity = new HttpEntity<>(dateSearchDiaryDto,getHeaders());

        ResponseEntity<List<DiaryDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "filter", HttpMethod.POST, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_DIARYS_FILTERED, response.getBody().size());

    }
    
    @Test
    public void getDiaryByPatientIdFilteredByDateRangeShouldReturnAllDiarysByPatientIdFiltered() {
        dateSearchDiaryDto.setPatientId(EXISTS_PATIENT_ID);
        
        LocalDate localDateStart = LocalDate.of(2023, 3, 1);
        
        LocalDate localDateEnd = LocalDate.of(2023, 3, 20);
        
        dateSearchDiaryDto.setStartDate(localDateStart);
        dateSearchDiaryDto.setEndDate(localDateEnd);
        
        HttpEntity<?> httpEntity = new HttpEntity<>(dateSearchDiaryDto,getHeaders());

        ResponseEntity<List<DiaryDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "filter", HttpMethod.POST, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_DIARYS_FILTERED, response.getBody().size());

    }
    
    @Test
    public void getDiaryWithPatientIdNotExistsWithEmptyFiltersNotShouldReturnDiarys() {
        dateSearchDiaryDto.setPatientId(NOT_EXISTS_PATIENT_ID);
        HttpEntity<?> httpEntity = new HttpEntity<>(dateSearchDiaryDto,getHeaders());

        ResponseEntity<List<DiaryDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "filter", HttpMethod.POST, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(EMPTY_DIARYS_FILTERED, response.getBody().size());

    }
    
    @Test
    public void getDiaryByPatientIdFilteredByDateNotExistsNotShouldReturnDiarys() {
        dateSearchDiaryDto.setPatientId(EXISTS_PATIENT_ID);
        
        LocalDate localDate = LocalDate.of(2025, 10, 5);
        
        dateSearchDiaryDto.setStartDate(localDate);
        dateSearchDiaryDto.setEndDate(localDate);
        
        HttpEntity<?> httpEntity = new HttpEntity<>(dateSearchDiaryDto,getHeaders());

        ResponseEntity<List<DiaryDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "filter", HttpMethod.POST, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(EMPTY_DIARYS_FILTERED, response.getBody().size());

    }
    

    @Test
    public void saveDiaryShouldCreateNewDiary() {
        diaryDto = new DiaryDto();
        diaryDto.setDescription("Hola esto es una descripcion");
        LocalDate localDate = LocalDate.of(2025, 12, 7);
        diaryDto.setCreateDate(localDate);
        this.patientDto.setId(2L);
        diaryDto.setPatient(this.patientDto);
        
        HttpEntity<?> httpEntity = new HttpEntity<>(diaryDto, getHeaders());

        ResponseEntity<DiaryDto> responseSave = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, httpEntity, DiaryDto.class);

        assertEquals(diaryDto.getDescription(), responseSave.getBody().getDescription());

    }
    
    
}