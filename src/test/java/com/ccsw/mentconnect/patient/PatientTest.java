package com.ccsw.mentconnect.patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.mentconnect.patient.dto.PatientDto;
import com.ccsw.mentconnect.user.logic.UserServiceImpl;
import com.ccsw.mentconnect.user.model.UserRepository;

@ExtendWith(MockitoExtension.class)
public class PatientTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserServiceImpl serviceImpl;

    @Mock
    private UserRepository userRepository;

    private PatientDto patientDto;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void saveWithExistsUsernameShouldThrowException() {

    }

    @Test
    public void saveWithNotExistsUsernameShouldCreateNewPatient() {

    }

}
