package com.capgemini.mentconnect.userrole.logic;

import com.capgemini.mentconnect.userrole.model.UserRoleEntity;
import com.capgemini.mentconnect.userrole.model.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UserRoleEntity> findByUser(Long userId) {

        return this.userRoleRepository.findByUserId(userId);
    }

}
