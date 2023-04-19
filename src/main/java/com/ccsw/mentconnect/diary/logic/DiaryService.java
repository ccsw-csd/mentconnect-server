package com.ccsw.mentconnect.diary.logic;

import java.util.List;

import com.ccsw.mentconnect.diary.dto.DateSearchDiaryDto;
import com.ccsw.mentconnect.diary.model.DiaryEntity;

public interface DiaryService {
    List<DiaryEntity> getDiaryByPatientId(DateSearchDiaryDto date);
}
