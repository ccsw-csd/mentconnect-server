package com.ccsw.mentconnect.questionnaire.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSearchDto;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireRepository;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Override
    public List<QuestionnaireEntity> findAll() {
        // TODO Auto-generated method stub
        return questionnaireRepository.findAll();
    }

    @Override
    public Page<QuestionnaireEntity> findPage(QuestionnaireSearchDto dto) {
        // TODO Auto-generated method stub

        QuestionnaireSpecification id = new QuestionnaireSpecification(
                new SearchCriteria(QuestionnaireEntity.ATT_ID, ":", dto.getId()));

        QuestionnaireSpecification description = new QuestionnaireSpecification(
                new SearchCriteria(QuestionnaireEntity.ATT_DESCRIPTION, ":", dto.getDescription()));

        QuestionnaireSpecification questionsNumber = new QuestionnaireSpecification(
                new SearchCriteria(QuestionnaireEntity.ATT_QUESTIONS_NUMBER, ":", dto.getQuestionsNumber()));

        QuestionnaireSpecification patiensNumber = new QuestionnaireSpecification(
                new SearchCriteria(QuestionnaireEntity.ATT_PATIENTS_NUMBER, ":", dto.getPatientsNumber()));

        QuestionnaireSpecification user = new QuestionnaireSpecification(
                new SearchCriteria(QuestionnaireEntity.ATT_USER, ":", dto.getUser().getName()));

        Specification<QuestionnaireEntity> spec = Specification.where(id).and(description).and(questionsNumber)
                .and(patiensNumber).and(user);

        return questionnaireRepository.findAll(spec, dto.getPageable());
    }

}
