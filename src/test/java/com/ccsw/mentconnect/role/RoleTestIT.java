package com.ccsw.mentconnect.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.role.dto.RoleDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RoleTestIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/role/";
    public static final Integer TOTAL_ROLE = 8;
    public static final Integer TOTAL_ROLE_TYPE = 2;
    public static final String LOCALHOST = "http://localhost:";
    public RoleDto newRole = new RoleDto();

    public static final String TYPE = "EXT";

    ParameterizedTypeReference<List<RoleDto>> responseTypeList = new ParameterizedTypeReference<List<RoleDto>>() {
    };

    @Test
    public void findAllShouldReturnAllRoles() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<RoleDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_ROLE, response.getBody().size());
    }

    @Test
    public void findAllShouldReturnFilteredRole() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
        ResponseEntity<List<RoleDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findByType/INT", HttpMethod.GET, httpEntity, responseTypeList);
        assertNotNull(response);
        assertEquals(TOTAL_ROLE_TYPE, response.getBody().size());
    }

}