package com.ccsw.mentconnect.user.logic;

import java.util.List;
import java.util.Optional;

import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.role.model.RoleEntity;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserFullDto;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
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

    @Override
    public List<UserEntity> findAll() {

        return userRepository.findAll();
    }

    @Override
    public Page<UserEntity> findPage(UserSearchDto dto) {

        UserSpecification id = new UserSpecification(new SearchCriteria(UserEntity.ATT_ID, ":", dto.getId()));
        UserSpecification username = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_USERNAME, ":", dto.getUsername()));
        UserSpecification name = new UserSpecification(new SearchCriteria(UserEntity.ATT_NAME, ":", dto.getName()));
        UserSpecification surnames = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_SURNAMES, ":", dto.getSurnames()));
        UserSpecification email = new UserSpecification(new SearchCriteria(UserEntity.ATT_EMAIL, ":", dto.getEmail()));

        Specification<UserEntity> spec = Specification.where(id).and(username).and(name).and(surnames).and(email);

        return userRepository.findAll(spec, dto.getPageable());
    }

}
