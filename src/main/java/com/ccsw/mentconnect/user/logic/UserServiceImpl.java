package com.ccsw.mentconnect.user.logic;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.patient.model.PatientEntity;
import com.ccsw.mentconnect.role.model.RoleEntity;
import com.ccsw.mentconnect.user.dto.UserFullDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;

/**
 * @author amirzoya
 *
 *         Clase que implementa los métodos de la interfaz UserService
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${user.password.length}")
    private Integer length;

    @Value("${user.password.chars}")
    private String chars;

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<UserEntity> autenticate(String username, String password) {

        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public UserEntity get(Long id) throws EntityNotFoundException {

        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public UserEntity saveUser(UserFullDto userDto) throws AlreadyExistsException {

        if (this.userRepository.existsByUsername(userDto.getUsername())) {
            throw new AlreadyExistsException();
        }

        UserEntity userEntity = this.beanMapper.map(userDto, UserEntity.class);
        String password = this.generatePassword();
        System.out.println("Contraseña generada: " + password); // TODO borrar cuando se envie el email.
        userEntity.setPassword(this.encryptSha256(password));

        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity modifyUser(UserFullDto userFullDto) throws EntityNotFoundException {

        if (userFullDto.getId() == null) {
            throw new EntityNotFoundException();
        }

        UserEntity updateUser = this.get(userFullDto.getId());
        updateUser.setName(userFullDto.getName());
        updateUser.setSurnames(userFullDto.getSurnames());
        updateUser.setEmail(userFullDto.getEmail());
        updateUser.setRoles(this.beanMapper.mapList(userFullDto.getRoles(), RoleEntity.class));
        updateUser.setPatients(this.beanMapper.mapList(userFullDto.getPatients(), PatientEntity.class));

        return this.userRepository.save(updateUser);
    }

    @Override
    public List<UserEntity> findAll() {

        return userRepository.findAll();
    }

    @Override
    public Page<UserEntity> findPage(UserSearchDto dto) {

        UserSpecification id = new UserSpecification(new SearchCriteria(UserEntity.ATT_ID, ":", dto.getId(),null));
        UserSpecification username = new UserSpecification(new SearchCriteria(UserEntity.ATT_USERNAME, ":", dto.getUsername(),null));
        UserSpecification name = new UserSpecification(new SearchCriteria(UserEntity.ATT_NAME, ":", dto.getName(),null));
        UserSpecification surnames = new UserSpecification(new SearchCriteria(UserEntity.ATT_SURNAMES, ":", dto.getSurnames(),null));
        UserSpecification email = new UserSpecification(new SearchCriteria(UserEntity.ATT_EMAIL, ":", dto.getEmail(),null));

        Specification<UserEntity> spec = Specification.where(id).and(username).and(name).and(surnames).and(email);

        return userRepository.findAll(spec, dto.getPageable());
    }

    public List<UserEntity> findFilter(String filter) {

        UserSpecification name = new UserSpecification(new SearchCriteria(UserEntity.ATT_NAME, ":", filter,null));
        UserSpecification surnames = new UserSpecification(new SearchCriteria(UserEntity.ATT_SURNAMES, ":", filter,null));

        Specification<UserEntity> spec = Specification.where(name).or(surnames);

        return userRepository.findAll(spec);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    private String generatePassword() {

        return RandomStringUtils.random(length, chars);
    }

    private String encryptSha256(String password) {

        return DigestUtils.sha256Hex(password);
    }

}
