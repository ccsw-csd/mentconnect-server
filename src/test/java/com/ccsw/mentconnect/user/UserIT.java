package com.ccsw.mentconnect.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.logic.UserSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/user/";
    public static final int TOTAL_USER = 2;
    public static final int TOTAL_USER_EMPTY = 0;
    public static final int TOTAL_USER_FIND = 1;
    public static final Long ID = 1L;
    public static final String NAME = "Admin";
    public static final String USERNAME = "admin";
    public static final String SURNAMES = "MentConnect";
    public static final String EMAIL = "admin@mentconnect.com";
    public static final String NAME_NOT_EXIST = "Maria";

    UserSearchDto dto = new UserSearchDto();

    ParameterizedTypeReference<List<UserDto>> responseTypeList = new ParameterizedTypeReference<List<UserDto>>() {
    };

    ParameterizedTypeReference<Page<UserDto>> responseTypePage = new ParameterizedTypeReference<Page<UserDto>>() {
    };

    @Test
    public void findAllShouldReturnAllUser() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_USER, response.getBody().size());
    }

    @Test
    public void findPageShouldFilteredUser() {

        dto.setId(ID);
        dto.setName(NAME);
        dto.setUsername(USERNAME);
        dto.setSurnames(SURNAMES);
        dto.setEmail(EMAIL);

        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_USER_FIND, response.getBody().getContent().size());
    }

    @Test
    public void NotExistsUserShouldReturnEmpty() {

        dto.setName(NAME_NOT_EXIST);

        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_USER_EMPTY, response.getBody().getContent().size());
    }

    @Test
    public void findPageExistsNameAndUsernameShouldReturnUsers() {

        dto.setName(NAME);
        dto.setUsername(USERNAME);

        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_USER_FIND, response.getBody().getContent().size());
    }

    @Test
    public void findPageExistsEmailShouldReturnUsers() {

        dto.setEmail(EMAIL);
        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_USER_FIND, response.getBody().getContent().size());
    }
}
