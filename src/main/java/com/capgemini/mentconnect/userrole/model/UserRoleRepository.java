package com.capgemini.mentconnect.userrole.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author amirzoya
 *
 *         Repositorio que accede a la información de la tabla user_role en base de datos
 *
 */
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findByUserId(Long userId);

}
