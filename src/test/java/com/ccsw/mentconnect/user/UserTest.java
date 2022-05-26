package com.ccsw.mentconnect.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.user.dto.UserFullDto;
import com.ccsw.mentconnect.user.logic.UserServiceImpl;
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

    @Mock
    BeanMapper beanMapper;

    private UserFullDto userDto;

    private UserEntity userEntityData;

    @BeforeEach
    public void setUp() {
        this.userDto = new UserFullDto();
        this.userDto.setName("Admin");
        this.userDto.setSurnames("Admin");
        this.userDto.setEmail("admin@meentconnect.com");
        userEntityData = new UserEntity();
        this.userEntityData.setName("Admin");
        this.userEntityData.setSurnames("Admin");
        this.userEntityData.setEmail("admin@meentconnect.com");
        ReflectionTestUtils.setField(userServiceImpl, "length", 4);
        ReflectionTestUtils.setField(userServiceImpl, "chars", "ABCDEFGHT");
    }

    @Test
    public void saveWithExistsUsernameShouldThrowException() throws AlreadyExistsException {

        this.userDto.setUsername(EXISTS_USER_USERNAME);
        UserEntity userEntity = mock(UserEntity.class);
        when(this.userRepository.existsByUsername(EXISTS_USER_USERNAME)).thenReturn(true);

        try {
            userServiceImpl.saveUser(userDto);
        } catch (AlreadyExistsException e) {
        }

        verify(this.userRepository, never()).save(userEntity);
    }

    @Test
    public void saveWithNotExistsUsernameShouldCreateNewUser() throws AlreadyExistsException {

        this.userDto.setUsername(NOT_EXISTS_USER_USERNAME);
        this.userEntityData.setUsername(NOT_EXISTS_USER_USERNAME);
        when(this.userRepository.existsByUsername(NOT_EXISTS_USER_USERNAME)).thenReturn(false);
        when(this.beanMapper.map(userDto, UserEntity.class)).thenReturn(userEntityData);
        ArgumentCaptor<UserEntity> userEntity = ArgumentCaptor.forClass(UserEntity.class);

        userServiceImpl.saveUser(userDto);

        verify(this.userRepository).save(userEntity.capture());
        assertEquals(userDto.getUsername(), userEntity.getValue().getUsername());
    }

    @Test
    public void modifyWithExistIdShouldModifyUser() throws EntityNotFoundException {

        this.userDto.setId(EXISTS_USER_ID);
        UserEntity userEntity = mock(UserEntity.class);
        when(this.userRepository.findById(EXISTS_USER_ID)).thenReturn(Optional.of(userEntity));

        this.userServiceImpl.modifyUser(userDto);

        verify(this.userRepository).save(userEntity);
    }

    @Test
    public void modifyWithNotExistIdShouldThrowException() throws EntityNotFoundException {

        this.userDto.setId(NOT_EXISTS_USER_ID);
        UserEntity userEntity = mock(UserEntity.class);
        doReturn(Optional.empty()).when(this.userRepository).findById(NOT_EXISTS_USER_ID);

        try {
            this.userServiceImpl.modifyUser(userDto);
        } catch (EntityNotFoundException e) {
        }

        verify(this.userRepository, never()).save(userEntity);
    }

}
