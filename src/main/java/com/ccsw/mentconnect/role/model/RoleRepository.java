package com.ccsw.mentconnect.role.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * @author amirzoya
 *
 *         Repositorio que accede a la información de la tabla user en base de
 *         datos
 *
 */
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity> findAll();

    
    List<RoleEntity> findByType(RoleTypeEnum type);

}
