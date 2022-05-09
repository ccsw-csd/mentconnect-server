package com.ccsw.mentconnect.role.logic;

import com.ccsw.mentconnect.role.model.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author amirzoya
 *
 *         Clase que implementa los métodos de la interfaz RoleService
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

}
