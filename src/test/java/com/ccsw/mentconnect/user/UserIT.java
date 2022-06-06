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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserFullDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/user/";

    public static final Integer TOTAL_USER = 2;
    public static final String NOT_EXISTS_USERNAME_USER = "jopepe";
    public static final String EXISTS_USERNAME_USER = "admin";
    public static final Long EXISTS_ID_USER = 1L;
    public static final Long NOT_EXISTS_ID_USER = 0L;

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
    public void saveWithExistsUsernameShouldThrowException() {

        UserFullDto dto = new UserFullDto();
        dto.setUsername(EXISTS_USERNAME_USER);
        dto.setName("");
        dto.setSurnames("");
        dto.setEmail("");

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST, httpEntity,
                UserFullDto.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void saveWithNotExistsUsernameShouldCreateNewUser() {

        Integer newUserSize = TOTAL_USER + 1;
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        UserFullDto dto = new UserFullDto();
        dto.setUsername(NOT_EXISTS_USERNAME_USER);
        dto.setName("");
        dto.setSurnames("");
        dto.setEmail("");

        ResponseEntity<UserFullDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.POST,
                new HttpEntity<>(dto, getHeaders()), UserFullDto.class);

        assertEquals(dto.getUsername(), response.getBody().getUsername());

        ResponseEntity<List<UserDto>> responseList = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseTypeList);

        assertEquals(newUserSize, responseList.getBody().size());

        UserDto userDto = responseList.getBody().stream()
                .filter(item -> item.getUsername().equals(NOT_EXISTS_USERNAME_USER)).findFirst().orElse(null);

        assertNotNull(userDto);
        assertEquals(userDto.getId(), response.getBody().getId());

    }

    @Test
    public void modifyWithNotExistIdShouldThrowException() {

        UserFullDto dto = new UserFullDto();
        dto.setId(NOT_EXISTS_ID_USER);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                UserFullDto.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void modifyWithExistIdShouldModifyUser() {

        UserFullDto dto = new UserFullDto();
        dto.setId(EXISTS_ID_USER);
        dto.setName(NOT_EXISTS_USERNAME_USER);
        dto.setSurnames("");
        dto.setEmail("");
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<UserFullDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                httpEntity, UserFullDto.class);
        assertNotNull(response.getBody());

        ResponseEntity<List<UserDto>> responseList = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseTypeList);

        assertEquals(TOTAL_USER, responseList.getBody().size());

        UserDto userDto = responseList.getBody().stream().filter(item -> item.getId().equals(EXISTS_ID_USER))
                .findFirst().orElse(null);

        assertNotNull(userDto);
        assertEquals(response.getBody().getName(), userDto.getName());
    }

    @Test
    public void findAllShouldReturnAllUser() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_USER, response.getBody().size());
    }

    @Test
    public void findPageShouldReturnPageUser() {

        UserSearchDto dto = new UserSearchDto();
        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_USER, response.getBody().getContent().size());
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
    public void findPageNotExistsUserShouldReturnEmpty() {

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

    @Test
    public void findExistsNameOrSurnamesShouldReturnUserFilter() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findFilter/" + SURNAMES, HttpMethod.GET, httpEntity,
                responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_USER, response.getBody().size());
    }

}
