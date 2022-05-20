package com.ccsw.mentconnect.user;

import com.ccsw.mentconnect.config.BaseITAbstract;
import com.ccsw.mentconnect.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/user/";

    public static final int TOTAL_USER = 2;
    
    public static final Long EXISTS_USER_ID = 1L;
    
    public static final Long NOT_EXISTS_USER_ID = 3L;
    
    private static final String NOT_EXIST_USERNAME_USER = "jopepe";
    
    private static final String EXIST_USERNAME_USER = "admin";
    
    private static final String NAME_USER = "Jopepe";

    private static final String SURNAME_USER = "MentConnect";
    
    public static final String EMAIL_USER = "jopepe@meentconnect.com";

    ParameterizedTypeReference<List<UserDto>> responseTypeList = new ParameterizedTypeReference<List<UserDto>>(){};
    ParameterizedTypeReference<UserDto> responseType = new ParameterizedTypeReference<UserDto>(){};

    @Test
    public void findAllShouldReturnAllUser() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeList);

        assertNotNull(response);
        assertEquals(TOTAL_USER, response.getBody().size());
    }
    
    @Test
    public void existsUsernameWhenSaveUserThrowException() {
      
      UserDto dto = new UserDto();
      dto.setUsername(EXIST_USERNAME_USER);
      
      ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "", HttpMethod.POST, new HttpEntity<>(dto), responseType);
      assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }
    
    @Test
    public void notExistsUsernameWhenSaveUser() {
      
      int newUserSize = TOTAL_USER + 1;
      
      HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
      UserDto dto = new UserDto();
      dto.setUsername(NOT_EXIST_USERNAME_USER);
      dto.setName(NAME_USER);
      dto.setSurnames(SURNAME_USER);
      dto.setEmail(EMAIL_USER);
      
      ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "", HttpMethod.POST, new HttpEntity<>(dto), responseType);
      assertEquals(dto, response.getBody());
      
      ResponseEntity<List<UserDto>> responseList = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeList);
      assertEquals(newUserSize, responseList.getBody().size());
      
      UserDto userDto = responseList.getBody().stream().filter(item -> item.getUsername().equals(NOT_EXIST_USERNAME_USER)).findFirst().orElse(null);
      assertNotNull(userDto);
      assertEquals(userDto, response.getBody());
     
    }
    
    @Test
    public void modifyUserWhenNotExistId() {
      HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
      UserDto dto = new UserDto();
      dto.setId(NOT_EXISTS_USER_ID);
      dto.setName(NAME_USER);
      dto.setSurnames(SURNAME_USER);
      dto.setEmail(EMAIL_USER);
      
      ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "", HttpMethod.PUT, new HttpEntity<>(dto), responseType);
      assertNotNull(response);
      
      ResponseEntity<List<UserDto>> responseList = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeList);
      assertEquals(TOTAL_USER, responseList.getBody().size());
      
      UserDto userDto = responseList.getBody().stream().filter(item -> item.getId().equals(NOT_EXISTS_USER_ID)).findFirst().orElse(null);
      assertNotNull(userDto);
      assertEquals(NAME_USER, userDto.getName());
      
    }
    
    @Test
    public void modifyUserWhenExistIdThrowException() {
      
      UserDto dto = new UserDto();
      dto.setId(EXISTS_USER_ID);
      
      ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "", HttpMethod.PUT, new HttpEntity<>(dto), responseType);
      assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
      
    }
    
}
