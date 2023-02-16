package com.ccsw.mentconnect.userpatient.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * @author maricel999
 *
 *         Repositorio que accede a la información de la tabla user_patient en
 *         base de datos
 *
 */
public interface UserPatientRepository extends CrudRepository<UserPatientEntity, Long> {

    List<UserPatientEntity> findByUserId(Long userId);
}