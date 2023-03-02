package com.ccsw.mentconnect.patient.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;
import com.ccsw.mentconnect.patient.dto.PatientSearchDto;
import com.ccsw.mentconnect.patient.model.PatientEntity;
import com.ccsw.mentconnect.patient.model.PatientRepository;
import com.ccsw.mentconnect.user.logic.UserService;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.devonfw.module.beanmapping.common.api.BeanMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserService userService;

    @Autowired
    BeanMapper beanMapper;

    @Override
    public PatientEntity getPatient(Long id) throws EntityNotFoundException {

        return this.patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public PatientEntity savePatient(PatientFullDto patientFullDto) throws AlreadyExistsException {

        if (this.patientRepository.existsByNif(patientFullDto.getNif()))
            throw new AlreadyExistsException();

        PatientEntity patientEntity = this.beanMapper.map(patientFullDto, PatientEntity.class);
        patientEntity.setUser(this.userService.saveUser(patientFullDto.getUser()));

        return this.patientRepository.save(patientEntity);
    }
    
    @Override
    public List<PatientEntity> findAll() {

        return patientRepository.findAll();
    }

    @Override
    public Page<PatientEntity> findPage(PatientSearchDto dto) {

        PatientSpecification id = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_ID, ":", dto.getId()));
        PatientSpecification nif = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_NIF, ":", dto.getNif()));
        PatientSpecification name = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_USER.concat(".".concat(UserEntity.ATT_NAME)), ":", dto.getUser().getName()));
        PatientSpecification surnames = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_USER.concat(".".concat(UserEntity.ATT_SURNAMES)), ":", dto.getUser().getSurnames()));
        PatientSpecification email = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_USER.concat(".".concat(UserEntity.ATT_EMAIL)), ":", dto.getUser().getEmail()));
        PatientSpecification dateBirth = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_DATE, ":", dto.getDateBirth()));
        PatientSpecification gender = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_GENDER, ":", dto.getGender()));
        PatientSpecification phone = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_PHONE, ":", dto.getPhone()));
        PatientSpecification sip = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_SIP, ":", dto.getSip()));
        PatientSpecification medical = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_MEDICAL, ":", dto.getMedicalHistory()));

        Specification<PatientEntity> spec = Specification.where(id).and(nif).and(name).and(surnames).and(email).and(dateBirth).and(gender).and(phone).and(sip).and(medical);

        return patientRepository.findAll(spec, dto.getPageable());
    }
    
    public List<PatientEntity> findFilter(String filter) {

        PatientSpecification nifSpec = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_NIF, ":", filter));
        PatientSpecification nameSpec = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_USER.concat(".".concat(UserEntity.ATT_NAME)), ":", filter));
        PatientSpecification surnamesSpec = new PatientSpecification(new SearchCriteria(PatientEntity.ATT_USER.concat(".".concat(UserEntity.ATT_SURNAMES)), ":", filter));
        Specification<PatientEntity> spec = Specification.where(nifSpec).or(nameSpec).or(surnamesSpec);

        return patientRepository.findAll(spec);
    }
    
    @Override
    public PatientEntity modifyPatient(PatientFullDto patientFullDto) throws EntityNotFoundException {
        if (patientFullDto.getId() == null) {
            throw new EntityNotFoundException();
        }
        
        PatientEntity patientEntity = this.beanMapper.map(patientFullDto, PatientEntity.class);
        patientEntity.setUser(this.userService.modifyUser(patientFullDto.getUser()));
        return this.patientRepository.save(patientEntity);
    }
    

}
