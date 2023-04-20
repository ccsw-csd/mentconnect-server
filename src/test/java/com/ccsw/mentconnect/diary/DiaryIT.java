package com.ccsw.mentconnect.diary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
    private DateSearchDiaryDto dateSearchDiaryDto = new DateSearchDiaryDto();

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
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        
        dateSearchDiaryDto.setStartDate(date);
        dateSearchDiaryDto.setEndDate(date);
        
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
        Instant instantStart = localDateStart.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date dateStart = Date.from(instantStart);
        
        LocalDate localDateEnd = LocalDate.of(2023, 3, 20);
        Instant instantEnd = localDateEnd.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date dateEnd = Date.from(instantEnd);
        
        dateSearchDiaryDto.setStartDate(dateStart);
        dateSearchDiaryDto.setEndDate(dateEnd);
        
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
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        
        dateSearchDiaryDto.setStartDate(date);
        dateSearchDiaryDto.setEndDate(date);
        
        HttpEntity<?> httpEntity = new HttpEntity<>(dateSearchDiaryDto,getHeaders());

        ResponseEntity<List<DiaryDto>> response = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "filter", HttpMethod.POST, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(EMPTY_DIARYS_FILTERED, response.getBody().size());

    }
    
}