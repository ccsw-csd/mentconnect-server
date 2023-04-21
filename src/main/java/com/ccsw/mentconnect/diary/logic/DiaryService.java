package com.ccsw.mentconnect.diary.logic;

import java.util.List;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.diary.dto.DateSearchDiaryDto;
import com.ccsw.mentconnect.diary.dto.DiaryDto;
import com.ccsw.mentconnect.diary.model.DiaryEntity;

public interface DiaryService {
    List<DiaryEntity> getDiaryByPatientId(DateSearchDiaryDto date);

    DiaryEntity saveOrUpdateDiary(Long id, DiaryDto diaryDto) throws AlreadyExistsException, EntityNotFoundException;

    void delete(Long id);

}
