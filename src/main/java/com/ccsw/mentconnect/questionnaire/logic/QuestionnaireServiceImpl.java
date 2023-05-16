package com.ccsw.mentconnect.questionnaire.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.config.security.UserUtils;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireInfoDto;
import com.ccsw.mentconnect.questionnaire.dto.QuestionnaireSearchDto;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireEntity;
import com.ccsw.mentconnect.questionnaire.model.QuestionnaireRepository;
import com.ccsw.mentconnect.questionnairequestion.dto.QuestionnaireQuestionDto;
import com.ccsw.mentconnect.questionnairequestion.model.QuestionnaireQuestionEntity;
import com.ccsw.mentconnect.security.dto.UserDetailsJWTDto;
import com.ccsw.mentconnect.user.logic.UserService;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    QuestionnaireRepository questionnaireRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserService userService;

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
    public QuestionnaireEntity saveOrUpdateQuestionnaire(QuestionnaireInfoDto questionnaireDto) throws AlreadyExistsException, EntityNotFoundException {
        UserDetailsJWTDto currentUser = UserUtils.getUserDetails();
        Optional<UserEntity> user = userRepository.findByUsername(currentUser.getUsername());
        if (questionnaireDto.getId() != null) {
            Optional<QuestionnaireEntity> existingQuestionnaire = questionnaireRepository.findById(questionnaireDto.getId());
            if (existingQuestionnaire.isPresent()) {
                QuestionnaireEntity updateQuestionnaire = this.getQuestionnaire(questionnaireDto.getId()); 
                if (!updateQuestionnaire.getUser().equals(user.orElse(null))) {
                    updateQuestionnaire.setUser(user.orElse(null));
                }
                updateQuestionnaire.setDescription(questionnaireDto.getDescription());
                updateQuestionnaire.setLastEditDate(LocalDate.now());
                List<QuestionnaireQuestionEntity> questionnaireQuestionEntities = new ArrayList<>();  
                for (QuestionnaireQuestionDto questionnaireQuestionDto : questionnaireDto.getQuestions()) {
                    QuestionnaireQuestionEntity questionnaireQuestionEntity = this.beanMapper.map(questionnaireQuestionDto, QuestionnaireQuestionEntity.class);
                    questionnaireQuestionEntity.setQuestionnaire(updateQuestionnaire); 
                    questionnaireQuestionEntities.add(questionnaireQuestionEntity);
                }
                updateQuestionnaire.setQuestions(new HashSet<>(questionnaireQuestionEntities));
                return questionnaireRepository.save(updateQuestionnaire);
            } else {
                throw new EntityNotFoundException(); 
            }
        }
        else { 
            if (this.questionnaireRepository.existsByDescription(questionnaireDto.getDescription())) {
                throw new AlreadyExistsException();
            }
            QuestionnaireEntity questionnaireEntity = this.beanMapper.map(questionnaireDto, QuestionnaireEntity.class);
            questionnaireEntity.addQuestionnaireToQuestions();
            user.ifPresent(questionnaireEntity::setUser); 
            questionnaireEntity.setCreateDate(LocalDate.now());
            questionnaireEntity.setLastEditDate(LocalDate.now());
            return this.questionnaireRepository.save(questionnaireEntity);
        }

    }

    @Override
    public QuestionnaireEntity getQuestionnaire(Long id) throws EntityNotFoundException {
        return this.questionnaireRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }



}