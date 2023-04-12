package com.ccsw.mentconnect.questionnaire.logic;

import java.util.List;

import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
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
    QuestionnaireRepository questionnaireRepository;

    @Override
    public List<QuestionnaireEntity> findAll() {
        return questionnaireRepository.findAll();
    }

    @Override
    public Page<QuestionnaireEntity> findPage(QuestionnaireSearchDto dto) {

        QuestionnaireSpecification id = new QuestionnaireSpecification(new SearchCriteria(QuestionnaireEntity.ATT_ID, ":", dto.getId(),null));
        QuestionnaireSpecification description = new QuestionnaireSpecification(new SearchCriteria(QuestionnaireEntity.ATT_DESCRIPTION, ":", dto.getDescription(),null));
        QuestionnaireSpecification user = new QuestionnaireSpecification(new SearchCriteria(QuestionnaireEntity.ATT_USER, ":", dto.getUser() != null &&
                dto.getUser().getId() != null ? beanMapper.map(dto.getUser(), UserEntity.class) : null, null));

        //PatientSpecification name = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_USER.concat(".".concat(UserEntity.ATT_NAME)), ":", dto.getUser().getName(),null));
        
        Specification<QuestionnaireEntity> spec = Specification.where(id).and(description).and(user);

        return questionnaireRepository.findAll(spec, dto.getPageable());
    }

}
