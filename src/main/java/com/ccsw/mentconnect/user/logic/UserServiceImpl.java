package com.ccsw.mentconnect.user.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
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

    @Autowired
    UserRepository userRepository;

    Page<UserEntity> users;

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
        UserSpecification username = new UserSpecification(new SearchCriteria(UserEntity.ATT_USERNAME, ":", dto.getUsername()));
        UserSpecification name = new UserSpecification(new SearchCriteria(UserEntity.ATT_NAME, ":", dto.getName()));
        UserSpecification surnames = new UserSpecification(new SearchCriteria(UserEntity.ATT_SURNAMES, ":", dto.getSurnames()));
        UserSpecification email = new UserSpecification(new SearchCriteria(UserEntity.ATT_EMAIL, ":", dto.getEmail()));

        Specification<UserEntity> spec = Specification.where(id).and(username).and(name).and(surnames).and(email);

        return userRepository.findAll(spec, dto.getPageable());
    }

}
