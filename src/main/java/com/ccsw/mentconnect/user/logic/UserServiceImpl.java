package com.ccsw.mentconnect.user.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

        UserSpecification idEqual = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_ID, ":", dto.getId().intValue()));

        UserSpecification nameEqual = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_NAME, "==", dto.getName()));

        UserSpecification usernameEqual = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_USERNAME, "==", dto.getUsername()));

        UserSpecification emailEqual = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_EMAIL, "==", dto.getEmail()));

        UserSpecification surnameEqual = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_SURNAMES, "==", dto.getSurnames()));

        users = userRepository.findAll(
                Specification.where(idEqual).or(surnameEqual).or(emailEqual).or(nameEqual).or(usernameEqual),
                dto.getPageable());

        return users;
    }

    // @Override
    // public Page<UserEntity> findFilterPage(UserSearchDto dto) {
    // return this.validate(dto);
    // }
    /*
     * @Override public void save(UserDto dto) {
     * 
     * user = new UserEntity(); user.setUsername(dto.getUsername());
     * user.setName(dto.getName()); user.setSurnames(dto.getSurnames());
     * user.setEmail(dto.getEmail());
     * 
     * 
     * this.userRepository.save(user);
     * 
     * }/*
     * 
     * /*@Override public void update(Long id, UserDto dto) {
     * 
     * user.getId(); user.setName(dto.getName());
     * user.setSurnames(dto.getSurnames()); user.setEmail(dto.getEmail());
     * 
     * this.userRepository.save(user);
     * 
     * }
     */

    /*
     * @Override public Page<UserEntity> find(Long id, String name, String surnames,
     * String email) {
     * 
     * return this.userRepository.find(id, name, surnames, email); }
     */

}
