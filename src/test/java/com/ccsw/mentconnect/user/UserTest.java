package com.ccsw.mentconnect.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.logic.UserServiceImpl;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;
import ma.glasnost.orika.MapperFacade;


@ExtendWith(MockitoExtension.class)
public class UserTest {
  
  public static final Long EXISTS_USER_ID = 1L;
  public static final Long NOT_EXISTS_USER_ID = 1L;

  public static final String EXISTS_USER_USERNAME = "admin";
  public static final String NOT_EXISTS_USER_USERNAME = "jopepe";
  
  @Mock
  private UserRepository userRepository;
  
  @Mock
  private MapperFacade mapperFacade;
  
  @InjectMocks
  private UserServiceImpl userServiceImpl;
  
  private UserDto userDto, userDtoSave;
  
  private AlreadyExistsException alreadyExistsException;
  
  @BeforeEach
  public void setUp()
  {
    this.userDto = new UserDto();
    this.userDto.setName("Admin");
    this.userDto.setSurnames("Admin");
    this.userDto.setSurnames("admin@meentconnect.com");
 
  }
  
  @Test
  public void existsUsernameWhenSaveUser() throws AlreadyExistsException
  {
    
    this.userDto.setUsername(EXISTS_USER_USERNAME);
    when(this.userRepository.existsByUsername(EXISTS_USER_USERNAME)).thenReturn(true);
    
    try {
      userDtoSave = this.mapperFacade.map(userServiceImpl.saveUser(userDto), UserDto.class);
    }catch(AlreadyExistsException e) {
      alreadyExistsException = e;
    }
    
    assertNull(userDtoSave);
    assertNotNull(this.alreadyExistsException);
    
  }
 
  @Test
  public void notExistsUsernameWhenSaveUser() throws AlreadyExistsException
  {
    this.userDto.setUsername(NOT_EXISTS_USER_USERNAME);
    
    when(this.userRepository.existsByUsername(NOT_EXISTS_USER_USERNAME)).thenReturn(false);
    ArgumentCaptor<UserEntity> userEntity = ArgumentCaptor.forClass(UserEntity.class);
    try {
      
      userServiceImpl.saveUser(userDto);
      verify(this.userRepository).save(userEntity.capture());
      
    }catch(AlreadyExistsException e) {
      alreadyExistsException = e;
    }
    
    assertNull(alreadyExistsException);
    assertEquals(this.userDto.getUsername(), userEntity.getValue().getUsername());
    
  }
 
}
