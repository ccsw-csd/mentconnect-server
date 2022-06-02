package com.ccsw.mentconnect.role.logic;

import java.util.List;

import com.ccsw.mentconnect.role.model.RoleEntity;
import com.ccsw.mentconnect.role.model.RoleTypeEnum;

/**
 * @author amirzoya
 *
 *         Interfaz que declara las operaciones de negocio de la entidad Role
 *
 */
public interface RoleService {

    List<RoleEntity> findAll();

    List<RoleEntity> findByType(RoleTypeEnum type);
}
