package com.ccsw.mentconnect.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.patient.dto.PatientFullDto;
import com.ccsw.mentconnect.patient.dto.PatientSearchDto;
import com.ccsw.mentconnect.patient.logic.PatientServiceImpl;
import com.ccsw.mentconnect.patient.model.PatientEntity;
import com.ccsw.mentconnect.patient.model.PatientRepository;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserFullDto;
import com.ccsw.mentconnect.user.logic.UserService;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.devonfw.module.beanmapping.common.api.BeanMapper;

@ExtendWith(MockitoExtension.class)
public class PatientTest {

    public static final String EXISTS_USER_USERNAME = "admin";
    public static final String NOT_EXISTS_USER_USERNAME = "jopepe";
    public static final String EXISTS_USER_NIF = "12345678X";
    public static final String NOT_EXISTS_USER_NIF = "12345678P";

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Mock
    private UserService userService;

    @Mock
    private PatientRepository patientUserRepository;

    @Mock
    private BeanMapper beanMapper;

    private PatientFullDto patientFullDto;

    private UserFullDto userFullDto;
    
    private UserDto userDto;
    
    private PatientSearchDto patientSearchDto;

    @BeforeEach
    public void setUp() {
        patientFullDto = new PatientFullDto();
        userFullDto = new UserFullDto();
        patientSearchDto = new PatientSearchDto();
		userDto = new UserDto();

        this.userFullDto.setName("Admin");
        this.userFullDto.setSurnames("Admin");
        this.userFullDto.setEmail("admin@meentconnect.com");
        this.patientFullDto.setUser(userFullDto);
        this.patientFullDto.setNif("12345678P");
        this.patientFullDto.setGender("H");
        this.patientFullDto.setPhone("123456789");
        
        this.userDto.setName("");
        this.userDto.setSurnames("");
        this.userDto.setEmail(""); 
        this.patientSearchDto.setUser(userDto);
        this.patientSearchDto.setNif(""); 
        this.patientSearchDto.setGender("");
        this.patientSearchDto.setPhone("");
    }

    @Test
    public void saveWithExistsUsernameAndNotExistNifShouldThrowException() throws AlreadyExistsException {

        PatientEntity patientEntity = mock(PatientEntity.class);
        patientFullDto.getUser().setUsername(EXISTS_USER_USERNAME);
        patientFullDto.setNif(NOT_EXISTS_USER_NIF);
        when(this.patientUserRepository.existsByNif(NOT_EXISTS_USER_NIF)).thenReturn(false);
        doThrow(new AlreadyExistsException()).when(this.userService).saveUser(patientFullDto.getUser());

        assertThrows(AlreadyExistsException.class, () -> patientServiceImpl.savePatient(patientFullDto));
        verify(this.patientUserRepository, never()).save(patientEntity);

    }

    @Test
    public void saveWithNotExistsUsernameAndExistsNifShouldThrowException() {

        patientFullDto.getUser().setUsername(NOT_EXISTS_USER_USERNAME);
        patientFullDto.setNif(EXISTS_USER_NIF);
        PatientEntity patientEntity = mock(PatientEntity.class);
        when(this.patientUserRepository.existsByNif(EXISTS_USER_NIF)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> patientServiceImpl.savePatient(patientFullDto));
        verify(this.patientUserRepository, never()).save(patientEntity);

    }

    @Test
    public void saveWithNotExistsUsernameAndNifShouldCreateNewPatient() throws AlreadyExistsException {

        UserEntity userEntity = mock(UserEntity.class);
        PatientEntity patientEntity = mock(PatientEntity.class);
        patientFullDto.getUser().setUsername(NOT_EXISTS_USER_USERNAME);
        patientFullDto.setNif(NOT_EXISTS_USER_NIF);
        when(this.patientUserRepository.existsByNif(NOT_EXISTS_USER_NIF)).thenReturn(false);
        when(this.beanMapper.map(patientFullDto, PatientEntity.class)).thenReturn(patientEntity);
        when(this.userService.saveUser(patientFullDto.getUser())).thenReturn(userEntity);

        this.patientServiceImpl.savePatient(patientFullDto);

        verify(this.patientUserRepository).save(patientEntity);

    }
    
    @Test
    public void findPageShouldReturnPatientPage() {
    	List<PatientEntity> list = new ArrayList<>();
    	list.add(mock(PatientEntity.class));
    	patientSearchDto.setPageable(PageRequest.of(0, 10));
        when(patientUserRepository.findAll(any(), eq(patientSearchDto.getPageable())))
                .thenReturn(new PageImpl<>(list, patientSearchDto.getPageable(), list.size()));

        Page<PatientEntity> page = patientServiceImpl.findPage(patientSearchDto);

        assertNotNull(page);
        assertEquals(1, page.getContent().size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findExistsNIFShouldReturnPatientFilter() {

        List<PatientEntity> list = new ArrayList<>();
        list.add(mock(PatientEntity.class));

        when(patientUserRepository.findAll(any(Specification.class))).thenReturn(list);
        List<PatientEntity> patients = patientServiceImpl.findFilter(EXISTS_USER_NIF);

        assertNotNull(patients);
        assertEquals(1, patients.size());

    }

}
