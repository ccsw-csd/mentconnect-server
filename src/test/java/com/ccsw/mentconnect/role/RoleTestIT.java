package com.ccsw.mentconnect.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.role.dto.RoleDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RoleTestIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/role/";
    public static final Integer TOTAL_ROLE = 8;
    public static final String LOCALHOST = "http://localhost:";
    public RoleDto newRole = new RoleDto();

    public static final Long ID = 3L;
    public static final String CODE = "PAT_INFO";
    public static final String TYPE = "EXT";
    public static final String TYPE_PARAM = "type";

    public static final String NOT_EXIST_TYPE = "UNK";
    ParameterizedTypeReference<List<RoleDto>> responseTypeList = new ParameterizedTypeReference<List<RoleDto>>() {
    };

    private String getUrlWithParams() {
        return UriComponentsBuilder.fromHttpUrl(LOCALHOST + port + SERVICE_PATH + "findByType")
                .queryParam(TYPE_PARAM, "{" + TYPE_PARAM + "}").encode().toUriString();
    }

    @Test
    public void findAllShouldReturnAllRoles() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
        ResponseEntity<List<RoleDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_ROLE, response.getBody().size());
    }

    @Test

    public void findRoleShouldFilteredType() {

        int ROLES_WITH_FILTER = 6;
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
        Map<String, Object> params = new HashMap<>();
        params.put(TYPE_PARAM, TYPE);

        ResponseEntity<List<RoleDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST, httpEntity,
                responseTypeList, params);

        assertNotNull(response);
        assertEquals(ROLES_WITH_FILTER, response.getBody().size());

    }

}
