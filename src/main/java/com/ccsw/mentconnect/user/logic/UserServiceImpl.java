package com.ccsw.mentconnect.user.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

        UserSpecification idEqual = new UserSpecification(new SearchCriteria(UserEntity.ATT_ID, ":", dto.getId()));

        UserSpecification nameEqual = new UserSpecification(

                new SearchCriteria(UserEntity.ATT_NAME, "==", dto.getName()));

        UserSpecification usernameEqual = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_USERNAME, "==", dto.getUsername()));

        // UserSpecification usernameEmpty = new UserSpecification(
        // new SearchCriteria(UserEntity.ATT_USERNAME, "==",
        // dto.getUsername().isEmpty()));
        UserSpecification emailEqual = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_EMAIL, "==", dto.getEmail()));

        UserSpecification surnameEqual = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_SURNAMES, "==", dto.getSurnames()));
        users = userRepository.findAll((UserSpecification.getSpecName(dto.getName()).and(null))
                .and(UserSpecification.getSpecSurname(dto.getSurnames()).and(null))
                .and(UserSpecification.getSpecId(dto.getId()).and(null))
                .and(UserSpecification.getSpecEmail(dto.getEmail()).and(null))
                .and(UserSpecification.getSpecUsername(dto.getUsername()).and(null))
                // validar que sean validas
                .or(idEqual).and(nameEqual).and(usernameEqual).and(surnameEqual).and(emailEqual)
        // .or(idEqual).and(nameEqual).and(surnameEqual).and(usernameEqual).and(emailEqual),
                , dto.getPageable());

        return users;
    }

}
