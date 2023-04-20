package com.ccsw.mentconnect.diary.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.diary.dto.DateSearchDiaryDto;
import com.ccsw.mentconnect.diary.dto.DiaryDto;
import com.ccsw.mentconnect.diary.model.DiaryEntity;
import com.ccsw.mentconnect.diary.model.DiaryRepository;
import com.ccsw.mentconnect.patient.logic.PatientService;
import com.ccsw.mentconnect.patient.model.PatientEntity;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    DiaryService diaryService;
    
    @Autowired
    DiaryRepository diaryRepository;
    
    @Autowired
    PatientService patientService;

    @Override
    public List<DiaryEntity> getDiaryByPatientId(DateSearchDiaryDto date) {
        if(date.getStartDate() != null && date.getEndDate() != null) {
            DiarySpecification patient = new DiarySpecification(
                    new SearchCriteria(DiaryEntity.ATT_PATIENT.concat(".".concat(PatientEntity.ATT_ID)), ":", date.getPatientId(),null));
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

            Sort sortByDate = Sort.by(Sort.Direction.DESC, DiaryEntity.ATT_CREATE_DATE); 
            return diaryRepository.findAll(Specification.where(patient).and(dateSpecs), sortByDate);
        }else {
            Sort sortByDate = Sort.by(Sort.Direction.DESC, DiaryEntity.ATT_CREATE_DATE); 
            return diaryRepository.findByPatientId(date.getPatientId(), sortByDate);
        }
        
    }

    @Override
    public DiaryEntity saveDiary(DiaryDto diaryDto) throws AlreadyExistsException {
        DiaryEntity diaryEntity = this.beanMapper.map(diaryDto, DiaryEntity.class);
        diaryEntity.setPatient(this.patientService.savePatient(diaryDto.getPatient()));
        return this.diaryRepository.save(diaryEntity);
    }

    @Override
    public DiaryEntity modifyDiary(DiaryDto diaryDto) throws EntityNotFoundException {
        if (diaryDto.getId() == null) {
            throw new EntityNotFoundException();
        }
        DiaryEntity diaryEntity = this.beanMapper.map(diaryDto, DiaryEntity.class);
        diaryEntity.setPatient(this.patientService.modifyPatient(diaryDto.getPatient()));
        return this.diaryRepository.save(diaryEntity);
    }

    @Override
    public void delete(Long id) {
        this.diaryRepository.deleteById(id);
        
    }

}
