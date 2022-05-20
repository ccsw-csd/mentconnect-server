package com.ccsw.mentconnect.user.logic;


import com.ccsw.mentconnect.common.exception.AlreadyExistsException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.user.dto.RandomPassword;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;

import org.springframework.beans.BeanUtils;


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

    @Override
    public UserEntity saveUser(UserDto userDto) throws AlreadyExistsException{
      
      UserEntity userEntity = new UserEntity();
      
      if(this.userRepository.existsByUsername(userDto.getUsername())) {
        throw new AlreadyExistsException();
      }
      
      userDto.setPassword(RandomPassword.generatePasswordSha256());
      BeanUtils.copyProperties(userDto, userEntity);
      this.userRepository.save(userEntity);
      
      return userEntity;
    }

    @Override
    public UserEntity modifyUser(UserDto userDto) throws EntityNotFoundException {
      
      UserEntity updateUser = null;
      
      if(userDto.getId() == null) {
        throw new EntityNotFoundException();
      }
      
      updateUser = this.get(userDto.getId());
      updateUser.setName(userDto.getName());
      updateUser.setUsername(userDto.getUsername());
      updateUser.setEmail(userDto.getEmail());
        
      this.userRepository.save(updateUser) ;
        
      return updateUser;
      
    }
}
