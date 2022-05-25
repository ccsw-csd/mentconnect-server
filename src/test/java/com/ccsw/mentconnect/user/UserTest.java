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

import com.ccsw.mentconnect.user.logic.UserSearchDto;
import com.ccsw.mentconnect.user.logic.UserServiceImpl;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    public static final int TOTAL_USERS = 1;

    @Test
    void findAllUsers() {

        List<UserEntity> listUser = new ArrayList<>();
        listUser.add(mock(UserEntity.class));
        when(userRepository.findAll()).thenReturn(listUser);
        List<UserEntity> users = userServiceImpl.findAll();
        assertNotNull(users);
        assertEquals(TOTAL_USERS, users.size());

    }

    @Test
    void findAllUserPage() {
        List<UserEntity> listUser = new ArrayList<>();
        listUser.add(mock(UserEntity.class));

        UserEntity dto = new UserEntity();
        UserSearchDto userPage = new UserSearchDto();
        dto.setId(1L);
        dto.setName("Admin");
        dto.setUsername("admin");
        dto.setSurnames("MentConnect");
        dto.setEmail("admin@mentconnect.com");
        listUser.add(dto);

        userPage.setId(dto.getId());
        userPage.setName(dto.getName());
        userPage.setUsername(dto.getUsername());
        userPage.setSurnames(dto.getSurnames());
        userPage.setEmail(dto.getEmail());

        when(userRepository.findAll()).thenReturn(listUser);
        List<UserEntity> resultAll = userServiceImpl.findAll();
        userPage.setPageable(PageRequest.of(0, 10));

        assertNotNull(resultAll);
        Page<UserEntity> resultPage = userRepository.findAll(userPage.getPageable());
        assertEquals(userPage.getName(), dto.getName());
        assertNull(resultPage);

    }
}
