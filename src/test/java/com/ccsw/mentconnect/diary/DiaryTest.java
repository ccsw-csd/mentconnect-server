package com.ccsw.mentconnect.diary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import com.ccsw.mentconnect.diary.dto.DateSearchDiaryDto;
import com.ccsw.mentconnect.diary.logic.DiaryServiceImpl;
import com.ccsw.mentconnect.diary.model.DiaryEntity;
import com.ccsw.mentconnect.diary.model.DiaryRepository;

@ExtendWith(MockitoExtension.class)
public class DiaryTest {

    public static final String EXISTS_DESCRIPTION = "staff";

    @Mock
    private DiaryRepository diaryRepository;

    @InjectMocks
    private DiaryServiceImpl diaryServiceImpl;
    
    private DateSearchDiaryDto dateSearchDiaryDto = new DateSearchDiaryDto();
    

    @Test
    public void getDiaryByPatientIdWithEmptyFiltersShouldReturnAllDiarysByPatientId() {
        dateSearchDiaryDto.setPatientId(1L);
        List<DiaryEntity> list = new ArrayList<>();

        list.add(mock(DiaryEntity.class));
        Sort sortByDate = Sort.by(Sort.Direction.DESC, DiaryEntity.ATT_CREATE_DATE); 
        when(diaryRepository.findByPatientId(1L, sortByDate)).thenReturn(list);

        List<DiaryEntity> diarys = diaryServiceImpl.getDiaryByPatientId(dateSearchDiaryDto);

        assertNotNull(diarys);
        assertEquals(1, diarys.size());
    }
    
    @Test
    public void getDiaryByPatientIdFilteredByDateExactShouldReturnAllDiarysByPatientIdFiltered() {
        dateSearchDiaryDto.setPatientId(1L);
        LocalDate localDate = LocalDate.of(2027, 6, 15);
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        
        dateSearchDiaryDto.setStartDate(date);
        dateSearchDiaryDto.setEndDate(date);

        List<DiaryEntity> list = new ArrayList<>();

        list.add(mock(DiaryEntity.class));
        List<DiaryEntity> diarys = diaryServiceImpl.getDiaryByPatientId(dateSearchDiaryDto);

        assertNotNull(diarys);
        assertEquals(0, diarys.size());
    }
    
}
