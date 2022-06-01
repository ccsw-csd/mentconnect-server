package com.ccsw.mentconnect.userrole.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.role.model.RoleRepository;
import com.ccsw.mentconnect.userrole.model.UserRoleEntity;
import com.ccsw.mentconnect.userrole.model.UserRoleRepository;

/**
 * @author amirzoya
 *
 *         Clase que implementa los métodos de la interfaz UserRoleService
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<UserRoleEntity> findByUser(Long userId) {

        return this.userRoleRepository.findByUserId(userId);
    }

}
