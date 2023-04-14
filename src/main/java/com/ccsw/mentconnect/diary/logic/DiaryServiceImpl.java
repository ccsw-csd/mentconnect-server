package com.ccsw.mentconnect.diary.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.diary.model.DiaryEntity;
import com.ccsw.mentconnect.diary.model.DiaryRepository;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    DiaryService diaryService;
    
    @Autowired
    DiaryRepository diaryRepository;
    
    @Override
    public List<DiaryEntity> getDiaryByPatientId(Long patientId){

        return diaryRepository.findDiaryByPatientId(patientId);
    }

}
