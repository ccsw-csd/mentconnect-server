package com.ccsw.mentconnect.user;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/user/";

    public static final int TOTAL_USER = 2;

    ParameterizedTypeReference<List<UserDto>> responseTypeList = new ParameterizedTypeReference<List<UserDto>>(){};

    ParameterizedTypeReference<Page<UserDto>> responseTypePage = new ParameterizedTypeReference<Page<UserDto>>(){};

    @Test
    public void findAllShouldReturnAllUser() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_USER, response.getBody().size());
    }

    @Test
    public void findPageShouldReturnPageUser() {

        UserSearchDto dto = new UserSearchDto();
        dto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage", HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_USER, response.getBody().getContent().size());
    }
}
