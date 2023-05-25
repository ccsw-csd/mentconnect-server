package com.ccsw.mentconnect.questionnaire.logic;

import java.time.LocalDate;
import java.util.List;

import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.user.logic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.config.security.UserUtils;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSimpleDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSearchDto;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireRepository;
import com.ccsw.mentconnect.security.dto.UserDetailsJWTDto;
import com.ccsw.mentconnect.user.model.UserEntity;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    QuestionnaireRepository questionnaireRepository;
    
    @Autowired
    UserService userService;

    @Override
    public QuestionnaireEntity getQuestionnaire(Long id) throws EntityNotFoundException {

        return this.questionnaireRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

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

        Specification<QuestionnaireEntity> spec = Specification.where(id).and(description).and(user);

        return questionnaireRepository.findAll(spec, dto.getPageable());
    }

    @Override
    public QuestionnaireEntity saveOrUpdateQuestionnaire(QuestionnaireSimpleDto dto) throws AlreadyExistsException, EntityNotFoundException {

        UserEntity user = getCurrentUser();
        QuestionnaireEntity questionnaire = dto.getId() != null ? this.getQuestionnaire(dto.getId()) : null;

        checkDescription(dto, questionnaire);

        QuestionnaireEntity entity = this.beanMapper.map(dto, QuestionnaireEntity.class);
        entity.addQuestionnaireToQuestions();
        entity.setPatients(questionnaire != null ? questionnaire.getPatients() : null);
        entity.setUser(user);
        entity.setCreateDate(questionnaire != null ? questionnaire.getCreateDate() : LocalDate.now());
        entity.setLastEditDate(LocalDate.now());

        return this.questionnaireRepository.save(entity);
    }

    private UserEntity getCurrentUser() throws EntityNotFoundException {

        UserDetailsJWTDto currentUser = UserUtils.getUserDetails();
        return userService.findByUsername(currentUser.getUsername()).orElseThrow(EntityNotFoundException::new);
    }

    private void checkDescription(QuestionnaireSimpleDto dto, QuestionnaireEntity questionnaire) throws AlreadyExistsException {

        if(questionnaire != null && !questionnaire.getDescription().equals(dto.getDescription())) {
            existsByDescription(dto.getDescription());
        } else if (questionnaire == null){
            existsByDescription(dto.getDescription());
        }
    }

    private void existsByDescription(String description) throws AlreadyExistsException {

        if(this.questionnaireRepository.existsByDescription(description)) {
            throw new AlreadyExistsException();
        }
    }

}