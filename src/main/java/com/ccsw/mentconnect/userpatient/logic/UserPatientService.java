package com.ccsw.mentconnect.userpatient.logic;

import java.util.List;

import com.ccsw.mentconnect.userpatient.model.UserPatientEntity;

/**
 * @author maricel999
 *
 *         Interfaz que declara las operaciones de negocio de la entidad User
 *
 */
public interface UserPatientService {

    List<UserPatientEntity> findByUser(Long userId);
}