package com.ccsw.mentconnect.questionnairepatient.logic;

import java.util.List;
import java.util.stream.Collectors;
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

    @Override
    public List<QuestionnaireEntity> getQuestionnaireAvailable(Long patientId) {

        List<QuestionnaireEntity> available = questionnaireService.findAll();

        List<QuestionnaireEntity> assigned = this.questionnairePatientRepository.findQuestionnairesByPatientId(patientId).stream().map(QuestionnairePatientEntity::getQuestionnaire).collect(Collectors.toList());

        return available.stream()
                .filter(q -> !assigned.stream().map(QuestionnaireEntity::getId).collect(Collectors.toList()).contains(q.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionnairePatientEntity> findQuestionnairesAssigned(QuestionnairePatientDto questionnairePatientDto) {

        QuestionnairePatientSpecification patient = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_PATIENT.concat(".".concat(PatientEntity.ATT_ID)), ":", questionnairePatientDto.getPatient().getId(),null));
        
        QuestionnairePatientSpecification startDateGrThEq = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_START_DATE, ">=", questionnairePatientDto.getStartDate(),null));
        QuestionnairePatientSpecification endDateLsThEq = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_END_DATE, "<=", questionnairePatientDto.getEndDate(), null));
        QuestionnairePatientSpecification startDateLsThEq = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_START_DATE, "<=", questionnairePatientDto.getStartDate(), null));
        QuestionnairePatientSpecification endDateGrThEq = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_END_DATE, ">=", questionnairePatientDto.getEndDate(), null));
        QuestionnairePatientSpecification startDateBtw = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_START_DATE, "<>", questionnairePatientDto.getStartDate(), questionnairePatientDto.getEndDate()));
        QuestionnairePatientSpecification endDateBtw = new QuestionnairePatientSpecification(
                new SearchCriteria(QuestionnairePatientEntity.ATT_END_DATE, "<>", questionnairePatientDto.getStartDate(), questionnairePatientDto.getEndDate()));

        Specification<QuestionnairePatientEntity> firstRange = startDateGrThEq.and(endDateLsThEq);
        Specification<QuestionnairePatientEntity> secondRange = startDateLsThEq.and(endDateGrThEq);
        Specification<QuestionnairePatientEntity> dateSpecs = firstRange.or(secondRange).or(startDateBtw).or(endDateBtw);

        return questionnairePatientRepository.findAll(Specification.where(patient).and(dateSpecs));
    }

    public Boolean checkQuestionnaireAssignable(QuestionnairePatientDto questionnairePatientDto) {

        return !findQuestionnairesAssigned(questionnairePatientDto).isEmpty();
    }

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

}
