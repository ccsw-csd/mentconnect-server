package com.ccsw.mentconnect.diary.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.diary.dto.DateSearchDiaryDto;
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

    @Override
    public List<DiaryEntity> filterDiary(DateSearchDiaryDto date) {
        DiarySpecification startDateGrThEq = new DiarySpecification(
                new SearchCriteria(DiaryEntity.ATT_CREATE_DATE, ">=", date.getStartDate(),null));
        DiarySpecification endDateLsThEq = new DiarySpecification(
                new SearchCriteria(DiaryEntity.ATT_CREATE_DATE, "<=", date.getEndDate(), null));
        DiarySpecification startDateLsThEq = new DiarySpecification(
                new SearchCriteria(DiaryEntity.ATT_CREATE_DATE, "<=", date.getStartDate(), null));
        DiarySpecification endDateGrThEq = new DiarySpecification(
                new SearchCriteria(DiaryEntity.ATT_CREATE_DATE, ">=", date.getEndDate(), null));
        DiarySpecification startDateBtw = new DiarySpecification(
                new SearchCriteria(DiaryEntity.ATT_CREATE_DATE, "<>", date.getStartDate(), date.getEndDate()));
        DiarySpecification endDateBtw = new DiarySpecification(
                new SearchCriteria(DiaryEntity.ATT_CREATE_DATE, "<>", date.getStartDate(), date.getEndDate()));

        Specification<DiaryEntity> firstRange = startDateGrThEq.and(endDateLsThEq);
        Specification<DiaryEntity> secondRange = startDateLsThEq.and(endDateGrThEq);
        Specification<DiaryEntity> dateSpecs = firstRange.or(secondRange).or(startDateBtw).or(endDateBtw);

        return diaryRepository.findAll(Specification.where(dateSpecs));
    }

}
