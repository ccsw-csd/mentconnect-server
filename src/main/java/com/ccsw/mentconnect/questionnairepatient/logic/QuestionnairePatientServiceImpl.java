package com.ccsw.mentconnect.questionnairepatient.logic;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.patient.model.PatientEntity;
import com.ccsw.mentconnect.questionnaire.logic.QuestionnaireService;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnairepatient.dto.QuestionnairePatientDto;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientEntity;
import com.ccsw.mentconnect.questionnairepatient.model.QuestionnairePatientRepository;

@Service
public class QuestionnairePatientServiceImpl implements QuestionnairePatientService {

    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    QuestionnairePatientRepository questionnairePatientRepository;
    
    @Autowired
    QuestionnaireService questionnaireService;
    
    @Override
    public List<QuestionnairePatientEntity> getQuestionnaireByPatientId(Long patientId) throws EntityNotFoundException {

        return questionnairePatientRepository.findQuestionnairesByPatientId(patientId);
    }

//    @Override
//    public List<QuestionnairePatientEntity> findAll() {
//
//        return questionnairePatientRepository.findAll();
//    }

    @Transactional
    @Override
    public QuestionnairePatientEntity saveQuestionnairePatient(QuestionnairePatientDto questionnairePatient) {
        QuestionnairePatientEntity questionnairePatientEntity = this.beanMapper.map(questionnairePatient, QuestionnairePatientEntity.class);
        return this.questionnairePatientRepository.save(questionnairePatientEntity);
    }

    @Override
    public void delete(Long id) {
        this.questionnairePatientRepository.deleteById(id);
    }

    @Override
    public List<QuestionnairePatientEntity> questionnaireAssigned(Long patientId, Date startDate, Date endDate) {
        QuestionnairePatientSpecification patient = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_PATIENT.concat(".".concat(PatientEntity.ATT_ID)), ":", patientId,null));
        
        QuestionnairePatientSpecification startDateGrThEq = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_START_DATE, ">=", startDate,null));
        QuestionnairePatientSpecification endDateLsThEq = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_END_DATE, "<=", endDate, null));
        QuestionnairePatientSpecification startDateLsThEq = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_START_DATE, "<=", startDate, null));
        QuestionnairePatientSpecification endDateGrThEq = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_END_DATE, ">=", endDate, null));
        QuestionnairePatientSpecification startDateBtw = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_START_DATE, "<>", startDate, endDate));
        QuestionnairePatientSpecification endDateBtw = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_END_DATE, "<>", startDate, endDate));

        Specification<QuestionnairePatientEntity> firstRange = startDateGrThEq.and(endDateLsThEq);
        Specification<QuestionnairePatientEntity> secondRange = startDateLsThEq.and(endDateGrThEq);
        Specification<QuestionnairePatientEntity> dateSpecs = firstRange.or(secondRange).or(startDateBtw).or(endDateBtw);
        return questionnairePatientRepository.findAll(Specification.where(patient).and(dateSpecs));
    }

    @Override
    public List<QuestionnaireEntity> questionnaireAvailable(Long patientId) throws EntityNotFoundException {
        List<QuestionnaireEntity> questionnaires = questionnaireService.findAll();
        List<QuestionnaireEntity> quest = this.questionnairePatientRepository.findQuestionnairesByPatientId(patientId).stream().map(QuestionnairePatientEntity::getQuestionnaire).collect(Collectors.toList());
        return questionnaires.stream().filter(q -> !quest.contains(q)).collect(Collectors.toList());
    }

}
