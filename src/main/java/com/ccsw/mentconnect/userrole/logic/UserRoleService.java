package com.ccsw.mentconnect.userrole.logic;

import java.util.List;

import com.ccsw.mentconnect.userrole.model.UserRoleEntity;

/**
 * @author amirzoya
 *
 *         Interfaz que declara las operaciones de negocio de la entidad User
 *
 */
public interface UserRoleService {

    List<UserRoleEntity> findByUser(Long userId);

}
