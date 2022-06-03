package com.ccsw.mentconnect.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.util.ReflectionTestUtils;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserFullDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;
import com.ccsw.mentconnect.user.logic.UserServiceImpl;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    public static final Long EXISTS_USER_ID = 1L;
    public static final Long NOT_EXISTS_USER_ID = 0L;
    public static final int TOTAL_USERS = 1;

    public static final String EXISTS_USER_USERNAME = "admin";
    public static final String NOT_EXISTS_USER_USERNAME = "jopepe";
    public static final String EXIST_NAME = "Pablo";
    public static final String NOT_EXIST_NAME = "Ana";
    public static final String EXIST_SURNAMES = "El Moussaoui";
    public static final String NOT_EXIST_SURNAMES = "Casa";
    public static final int TOTAL_USERS_FILTER = 2;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    BeanMapper beanMapper;

    private UserFullDto userDto;

    @BeforeEach
    public void setUp() {
        this.userDto = new UserFullDto();
        this.userDto.setName("Admin");
        this.userDto.setSurnames("Admin");
        this.userDto.setSurnames("admin@meentconnect.com");
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
        when(this.userRepository.existsByUsername(NOT_EXISTS_USER_USERNAME)).thenReturn(false);
        UserEntity userEntity = mock(UserEntity.class); // TODO revisar, hacer con captor
        when(this.beanMapper.map(this.userDto, UserEntity.class)).thenReturn(userEntity);

        userServiceImpl.saveUser(userDto);

        verify(this.userRepository).save(userEntity);
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

    @Test
    public void findAllShouldReturnAllUsers() {

        List<UserEntity> list = new ArrayList<>();
        list.add(mock(UserEntity.class));
        when(userRepository.findAll()).thenReturn(list);

        List<UserEntity> users = userServiceImpl.findAll();

        assertNotNull(users);
        assertEquals(TOTAL_USERS, users.size());
    }

    @Test
    public void findPageShouldReturnUsersPage() {

        UserSearchDto dto = new UserSearchDto();
        dto.setPageable(PageRequest.of(0, 10));

        List<UserEntity> list = new ArrayList<>();
        list.add(mock(UserEntity.class));

        when(userRepository.findAll(any(), eq(dto.getPageable())))
                .thenReturn(new PageImpl<>(list, dto.getPageable(), list.size()));

        Page<UserEntity> page = userServiceImpl.findPage(dto);

        assertNotNull(page);
        assertEquals(TOTAL_USERS, page.getContent().size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findExistsNameOrSurnamesShouldReturnUserFilter() {
        UserDto user = mock(UserDto.class);
        user.setName(EXIST_NAME);
        user.setSurnames(EXIST_SURNAMES);
        List<UserEntity> list = new ArrayList<>();
        list.add(mock(UserEntity.class));

        when(userRepository.findAll(any(Specification.class))).thenReturn(list);
        List<UserEntity> users = userServiceImpl.findByNameOrSurnames(EXIST_NAME, EXIST_SURNAMES);

        assertNotNull(users);
        assertEquals(TOTAL_USERS, users.size());

    }
}
