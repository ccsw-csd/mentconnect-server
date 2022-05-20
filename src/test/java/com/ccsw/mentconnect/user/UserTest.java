package com.ccsw.mentconnect.user;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.logic.UserServiceImpl;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;
import ma.glasnost.orika.MapperFacade;


@ExtendWith(MockitoExtension.class)
public class UserTest {
  
  public static final Long EXISTS_USER_ID = 1L;
  public static final Long NOT_EXISTS_USER_ID = 0L;

  public static final String EXISTS_USER_USERNAME = "admin";
  public static final String NOT_EXISTS_USER_USERNAME = "jopepe";
  
  @Mock
  private UserRepository userRepository;
  
  @Mock
  private MapperFacade mapperFacade;
  
  @InjectMocks
  private UserServiceImpl userServiceImpl;
  
  private UserDto userDto, userDtoSave;
  
  @BeforeEach
  public void setUp(){
    this.userDto = new UserDto();
    this.userDto.setName("Admin");
    this.userDto.setSurnames("Admin");
    this.userDto.setSurnames("admin@meentconnect.com");
 
  }
  
  @Test
  public void existsUsernameWhenSaveUserThrowException() throws AlreadyExistsException{
    
    this.userDto.setUsername(EXISTS_USER_USERNAME);
    UserEntity userEntity = mock(UserEntity.class);
    when(this.userRepository.existsByUsername(EXISTS_USER_USERNAME)).thenReturn(true);
    
    try {
      userServiceImpl.saveUser(userDto);
    }catch(AlreadyExistsException e) {}
    
    verify(this.userRepository, never()).save(userEntity);
    
  }
 
  @Test
  public void notExistsUsernameWhenSaveUser() throws AlreadyExistsException{
    
    this.userDto.setUsername(NOT_EXISTS_USER_USERNAME);
    when(this.userRepository.existsByUsername(NOT_EXISTS_USER_USERNAME)).thenReturn(false);
    ArgumentCaptor<UserEntity> userEntity = ArgumentCaptor.forClass(UserEntity.class);
    
    this.userDtoSave = this.mapperFacade.map(userServiceImpl.saveUser(userDto), UserDto.class);
    
    verify(this.userRepository).save(userEntity.capture());
    assertEquals(this.userDto.getUsername(), userEntity.getValue().getUsername());
    assertEquals(this.userDtoSave, this.userDto);
    
  }
  
  @Test
  public void modifyUserWhenExistId() throws EntityNotFoundException{
    
    this.userDto.setId(EXISTS_USER_ID); 
    UserEntity userEntity = mock(UserEntity.class);
    when(this.userRepository.findById(EXISTS_USER_ID)).thenReturn(Optional.of(userEntity));
     
    this.userDtoSave = this.mapperFacade.map(this.userServiceImpl.modifyUser(userDto), UserDto.class);

    verify(this.userRepository).save(userEntity);
    assertEquals(this.userDtoSave, this.userDto);
    
  }
  
  @Test
  public void modifyUserWhenNotExistId() throws EntityNotFoundException{
    
    this.userDto.setId(NOT_EXISTS_USER_ID); 
    UserEntity userEntity = mock(UserEntity.class);
    doReturn(Optional.empty()).when(this.userRepository).findById(NOT_EXISTS_USER_ID);

    try {  
      this.userServiceImpl.modifyUser(userDto);
    }catch(EntityNotFoundException e) {}
    
    verify(this.userRepository, never()).save(userEntity);
    
  }
   
}
