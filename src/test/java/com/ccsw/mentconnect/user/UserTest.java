package com.ccsw.mentconnect.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.logic.SearchCriteria;
import com.ccsw.mentconnect.user.logic.UserSearchDto;
import com.ccsw.mentconnect.user.logic.UserServiceImpl;
import com.ccsw.mentconnect.user.logic.UserSpecification;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserTest {
  
    public static final Long EXISTS_USER_ID = 1L;
    public static final Long NOT_EXISTS_USER_ID = 0L;

    public static final String EXISTS_USER_USERNAME = "admin";
    public static final String NOT_EXISTS_USER_USERNAME = "jopepe";
    

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;
    
    private UserDto userDto;

    @BeforeEach
    public void setUp(){
      this.userDto = new UserDto();
      this.userDto.setName("Admin");
      this.userDto.setSurnames("Admin");
      this.userDto.setSurnames("admin@meentconnect.com");

    }
    
    @Test
    void findAllUsers() {
        List<UserEntity> listUser = new ArrayList<>();
        listUser.add(mock(UserEntity.class));
        when(userRepository.findAll()).thenReturn(listUser);
        List<UserEntity> users = userServiceImpl.findAll();
        assertNotNull(users);
        assertEquals(1, users.size());

    }

    @Test
    void findAllPageUsers() {

    }

    @Test
    void correct_specifications() {
        UserEntity dto = mock(UserEntity.class);

        dto.setId(1L);
        dto.setName("Admin");
        dto.setUsername("admin");
        dto.setSurnames("MentConnect");
        dto.setEmail("admin@mentconnect.com");
        UserSearchDto userPage = mock(UserSearchDto.class);
        userPage.setPageable(PageRequest.of(0, 10));
        UserSpecification specId = new UserSpecification(new SearchCriteria("id", ":", dto.getId().intValue()));
        UserSpecification specName = new UserSpecification(new SearchCriteria("name", "==", dto.getName()));
        UserSpecification specUsername = new UserSpecification(new SearchCriteria("username", "==", dto.getUsername()));
        UserSpecification specSurnames = new UserSpecification(new SearchCriteria("surnames", "==", dto.getSurnames()));
        UserSpecification specEmail = new UserSpecification(new SearchCriteria("email", "==", dto.getEmail()));
        List<UserEntity> result = userRepository
                .findAll(Specification.where(specId).and(specName).and(specUsername).and(specSurnames).and(specEmail));
        Page<UserEntity> result1 = userServiceImpl.findPage(userPage);
        assertNull(result1);

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

      userServiceImpl.saveUser(userDto);

      verify(this.userRepository).save(userEntity.capture());
      assertEquals(this.userDto.getUsername(), userEntity.getValue().getUsername());

    }

    @Test
    public void modifyUserWhenExistId() throws EntityNotFoundException{

      this.userDto.setId(EXISTS_USER_ID); 
      UserEntity userEntity = mock(UserEntity.class);
      when(this.userRepository.findById(EXISTS_USER_ID)).thenReturn(Optional.of(userEntity));

      this.userServiceImpl.modifyUser(userDto);

      verify(this.userRepository).save(userEntity);

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
