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
    public void findPageExistName() {

        UserSearchDto dto = new UserSearchDto();
        dto.setId(1L);
        dto.setName("Admin");
        dto.setUsername("admin");
        dto.setSurnames("MentConnect");
        dto.setEmail("admin@mentconnect.com");
        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    public void findPageNotExist() {

        UserSearchDto dto = new UserSearchDto();
        dto.setId(0L);
        dto.setName("Maria");
        dto.setUsername("");
        dto.setSurnames("");
        dto.setEmail("");
        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_USER_EMPTY, response.getBody().getContent().size());
    }

    @Test
    public void findExistNameAndUsername() {

        UserSearchDto dto = new UserSearchDto();
        dto.setId(0L);
        dto.setName("Admin");
        dto.setUsername("admin");
        dto.setSurnames("");
        dto.setEmail("");
        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_USER_EMPTY, response.getBody().getContent().size());
    }

    @Test
    public void findPageExistEmail() {

        UserSearchDto dto = new UserSearchDto();
        dto.setId(0L);
        dto.setName("");
        dto.setUsername("");
        dto.setSurnames("");
        dto.setEmail("admin@mentconnect.com");
        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_USER_EMPTY, response.getBody().getContent().size());
    }
}
