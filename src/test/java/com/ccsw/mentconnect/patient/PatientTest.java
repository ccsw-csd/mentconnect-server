package com.ccsw.mentconnect.patient;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.patient.dto.PatientDto;
import com.ccsw.mentconnect.patient.logic.PatientServiceImpl;
import com.ccsw.mentconnect.patient.model.PatientEntity;
import com.ccsw.mentconnect.patient.model.PatientRepository;
import com.ccsw.mentconnect.user.dto.UserFullDto;
import com.ccsw.mentconnect.user.logic.UserService;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.devonfw.module.beanmapping.common.api.BeanMapper;

@ExtendWith(MockitoExtension.class)
public class PatientTest {

    public static final String EXISTS_USER_USERNAME = "admin";
    public static final String NOT_EXISTS_USER_USERNAME = "jopepe";

    public static final Long EXISTS_USER_ID = 1L;
    public static final Long NOT_EXISTS_USER_ID = 0L;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Mock
    private UserService userService;

    @Mock
    private PatientRepository patientUserRepository;

    @Mock
    private BeanMapper beanMapper;

    private PatientDto patientDto;

    private UserFullDto userFullDto;

    @BeforeEach
    public void setUp() {

        patientDto = new PatientDto();
        userFullDto = new UserFullDto();

        this.userFullDto.setName("Admin");
        this.userFullDto.setSurnames("Admin");
        this.userFullDto.setEmail("admin@meentconnect.com");
        this.patientDto.setUser(userFullDto);
        this.patientDto.setNif("12345678P");
        this.patientDto.setGender('H');
        this.patientDto.setPhone("123456789");
    }

    @Test
    public void saveWithExistsUsernameShouldThrowException() throws AlreadyExistsException {

        PatientEntity patientEntity = mock(PatientEntity.class);
        patientDto.getUser().setUsername(EXISTS_USER_USERNAME);
        doThrow(new AlreadyExistsException()).when(this.userService).saveUser(patientDto.getUser());

        assertThrows(AlreadyExistsException.class, () -> patientServiceImpl.savePatient(patientDto));
        verify(this.patientUserRepository, never()).save(patientEntity);

    }

    @Test
    public void saveWithNotExistsUsernameShouldCreateNewPatient() throws AlreadyExistsException {

        UserEntity userEntity = mock(UserEntity.class);
        PatientEntity patientEntity = mock(PatientEntity.class);
        patientDto.getUser().setUsername(NOT_EXISTS_USER_USERNAME);
        when(this.beanMapper.map(patientDto, PatientEntity.class)).thenReturn(patientEntity);
        when(this.userService.saveUser(patientDto.getUser())).thenReturn(userEntity);

        this.patientServiceImpl.savePatient(patientDto);

        verify(this.patientUserRepository).save(patientEntity);

    }

}
