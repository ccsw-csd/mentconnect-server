package com.ccsw.mentconnect.questionnaire.logic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSearchDto;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireRepository;
import com.ccsw.mentconnect.user.model.UserEntity;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    EntityManager em;

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Override
    public List<QuestionnaireEntity> findAll() {

        return questionnaireRepository.findAll();
    }

    @Override
    public Page<QuestionnaireEntity> findPage(QuestionnaireSearchDto dto) {

        Long id = dto.getId();
        String description = dto.getDescription();
        Integer questionsNumber = dto.getQuestionsNumber();
        Integer patientsNumber = dto.getPatientsNumber();
        UserEntity user = null;
        if (dto.getUser() != null && dto.getUser().getId() != null) {
            user = beanMapper.map(dto.getUser(), UserEntity.class);
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<QuestionnaireEntity> cq = cb.createQuery(QuestionnaireEntity.class);

        Root<QuestionnaireEntity> questionnaire = cq.from(QuestionnaireEntity.class);

        if (id == null && description == null && questionsNumber == null && patientsNumber == null && user == null) {
            cq.select(questionnaire);
        } else {
            List<Predicate> filterPredicates = new ArrayList<>();
            if (id != null) {
                filterPredicates.add(cb.equal(questionnaire.get("id"), id));
            }
            if (description != null) {
                filterPredicates.add(cb.like(questionnaire.get("description"), "%" + description + "%"));
            }
            if (questionsNumber != null) {
                filterPredicates.add(cb.count(questionnaire.get("questions")));
                System.out.println("Predicado questions: " + filterPredicates.toArray());

            }

            if (user != null) {
                filterPredicates.add(cb.equal(questionnaire.get("user"), user));

            }

            cq.where(filterPredicates.toArray(new Predicate[] {}));
        }

        System.out.println("CQ: " + cq);

        TypedQuery<QuestionnaireEntity> query = em.createQuery(cq);

        System.out.println("QUERY: " + query);

        List<QuestionnaireEntity> list = query.getResultList();

        System.out.println(query.getResultList());

        Page<QuestionnaireEntity> results = new PageImpl<QuestionnaireEntity>(list, dto.getPageable(), list.size());

        return results;

        /*
         * QuestionnaireSpecification id = new QuestionnaireSpecification( new
         * SearchCriteria(QuestionnaireEntity.ATT_ID, ":", dto.getId()));
         * 
         * QuestionnaireSpecification description = new QuestionnaireSpecification( new
         * SearchCriteria(QuestionnaireEntity.ATT_DESCRIPTION, ":",
         * dto.getDescription()));
         * 
         * QuestionnaireSpecification questionsNumber = new QuestionnaireSpecification(
         * new SearchCriteria(QuestionnaireEntity.ATT_QUESTIONS_NUMBER, ":",
         * dto.getQuestionsNumber()));
         * 
         * QuestionnaireSpecification patientsNumber = new QuestionnaireSpecification(
         * new SearchCriteria(QuestionnaireEntity.ATT_PATIENTS_NUMBER, ":",
         * dto.getPatientsNumber()));
         * 
         * QuestionnaireSpecification user = new QuestionnaireSpecification( new
         * SearchCriteria(QuestionnaireEntity.ATT_USER, ":", dto.getUser() != null &&
         * dto.getUser().getId() != null ? beanMapper.map(dto.getUser(),
         * UserEntity.class) : null));
         * 
         * Specification<QuestionnaireEntity> spec =
         * Specification.where(id).and(description).and(questionsNumber)
         * .and(patientsNumber).and(user);
         * 
         * return questionnaireRepository.findAll(spec, dto.getPageable());
         */
    }

}
