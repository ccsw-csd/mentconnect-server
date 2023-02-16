package com.ccsw.mentconnect.userpatient.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.userpatient.model.UserPatientEntity;
import com.ccsw.mentconnect.userpatient.model.UserPatientRepository;

@Service
public class UserPatientServiceImpl implements UserPatientService {

    @Autowired
    UserPatientRepository userPatientRepository;

    @Override
    public List<UserPatientEntity> findByUser(Long userId) {

        return this.userPatientRepository.findByUserId(userId);
    }

}