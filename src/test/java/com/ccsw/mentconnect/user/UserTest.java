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

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

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
}
