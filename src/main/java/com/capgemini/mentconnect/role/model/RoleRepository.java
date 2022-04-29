package com.capgemini.mentconnect.role.model;

import org.springframework.data.repository.CrudRepository;

/**
 * @author amirzoya
 *
 *         Repositorio que accede a la información de la tabla user en base de datos
 *
 */
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

}
