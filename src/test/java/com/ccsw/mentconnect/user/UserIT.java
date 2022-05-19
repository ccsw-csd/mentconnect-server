package com.ccsw.mentconnect.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.mentconnect.common.pagination.Paging;
import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/user/";
    public static final int TOTAL_USER = 2;

    public static final String PARAM_ID = "id";
    public static final String PARAM_NAME = "name";

    public static final String PARAM_SURNAME = "surnames";
    public static final int TOTAL_USERS = 4;
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_EMAIL = "email";
    private static final int PAGE_SIZE = 1;
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
    public void findWithoutFiltersShouldReturnAllLoadInDB() {
        // List<UserDto> listUser = new ArrayList();
        UserSearchDto dto = new UserSearchDto();

        // listUser.add(dto);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        Paging paging = new Paging();
        paging.setPageNumber(0);
        paging.setPageSize(10);

        int USER_WITH_FILTER = 1;
        dto.setPageable(paging);

        Map<String, Object> params = new HashMap<>();
        params.put("PARAM_ID", null);
        params.put("PARAM_NAME", null);
        params.put("PARAM_USERNAME", null);
        params.put("PARAM_SURNAME", null);
        params.put("PARAM_EMAIL", null);

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage, params);

        assertNotNull(response);
        assertEquals(USER_WITH_FILTER, response.getBody().getContent().size());

    }

}
