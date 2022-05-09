package com.ccsw.mentconnect.userrole.logic;

import com.ccsw.mentconnect.userrole.model.UserRoleEntity;

import java.util.List;

/**
 * @author amirzoya
 *
 *         Interfaz que declara las operaciones de negocio de la entidad User
 *
 */
public interface UserRoleService {

    List<UserRoleEntity> findByUser(Long userId);

}
