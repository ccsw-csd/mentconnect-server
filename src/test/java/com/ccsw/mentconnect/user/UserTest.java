package com.ccsw.mentconnect.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.ccsw.mentconnect.user.logic.SearchCriteria;
import com.ccsw.mentconnect.user.logic.UserSearchDto;
import com.ccsw.mentconnect.user.logic.UserServiceImpl;
import com.ccsw.mentconnect.user.logic.UserSpecification;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    final Long ID = 1L;
    final String NAME = "Admin";
    final String USERNAME = "admin";
    final String SURNAMES = "MentConnect";
    final String EMAIL = "admin@mentconnect.com";

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntity userJohn;
    private UserEntity userTom;

    @Test
    void findAllUsers() {
        List<UserEntity> listUser = new ArrayList<>();
        // añadimos la clase UserEntity a mock
        listUser.add(mock(UserEntity.class));
        when(userRepository.findAll()).thenReturn(listUser);
        List<UserEntity> users = userServiceImpl.findAll();
        assertNotNull(users);
        assertEquals(1, users.size());

    }

    @Test
    void findAllPageUsers() {
        // List<UserEntity> listUser = new ArrayList<>();
        // UserSearchDto userPage = mock(UserSearchDto.class);
        // UserDto dto = mock(UserDto.class);
        // dto.setId(ID);
        // dto.setName(NAME);
        // dto.setUsername(USERNAME);
        // dto.setSurnames(SURNAMES);
        // dto.setEmail(EMAIL);
        // userPage.setPageable(PageRequest.of(0, 5));
        // añadimos la clase UserEntity a mock
        // listUser.add(mock(UserEntity.class));
        // when(userRepository.findAll()).thenReturn(listUser);
        // Page<UserEntity> users = userServiceImpl.findPage(userPage);
        // assertNotNull(users);
        // assertEquals(1, users.getSize());

    }

    @Test
    void correct_specifications() {
        UserEntity dto = mock(UserEntity.class);
        UserSearchDto userPage = mock(UserSearchDto.class);
        userPage.setPageable(PageRequest.of(0, 10));
        UserSpecification specId = new UserSpecification(new SearchCriteria("id", ":", dto.getId().intValue()));
        UserSpecification specName = new UserSpecification(new SearchCriteria("name", ":", dto.getName()));
        UserSpecification specUsername = new UserSpecification(new SearchCriteria("username", ":", dto.getUsername()));
        UserSpecification specSurnames = new UserSpecification(new SearchCriteria("surnames", ":", dto.getSurnames()));
        UserSpecification specEmail = new UserSpecification(new SearchCriteria("email", ":", dto.getEmail()));
        List<UserEntity> result = userRepository
                .findAll(Specification.where(specId).or(specName).or(specUsername).or(specSurnames).or(specEmail));
        Page<UserEntity> result1 = userServiceImpl.findPage(userPage);
        assertNull(result1);

    }
}
