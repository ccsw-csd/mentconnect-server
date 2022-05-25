package com.ccsw.mentconnect.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
import org.springframework.data.domain.PageImpl;
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
    void findAllShouldReturnAllUsers() {

        List<UserEntity> list = new ArrayList<>();
        list.add(mock(UserEntity.class));
        when(userRepository.findAll()).thenReturn(list);

        List<UserEntity> users = userServiceImpl.findAll();

        assertNotNull(users);
        assertEquals(TOTAL_USERS, users.size());
    }

    @Test
    void findPageShouldReturnUsersPage() {

        UserSearchDto dto = new UserSearchDto();
        dto.setPageable(PageRequest.of(0, 10));

        List<UserEntity> list = new ArrayList<>();
        list.add(mock(UserEntity.class));

        when(userRepository.findAll(any(), eq(dto.getPageable()))).thenReturn(new PageImpl<>(list, dto.getPageable(), list.size()));

        Page<UserEntity> page = userServiceImpl.findPage(dto);

        assertNotNull(page);
        assertEquals(TOTAL_USERS, page.getContent().size());
    }
}
