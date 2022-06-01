package com.ccsw.mentconnect.role.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.role.model.RoleEntity;
import com.ccsw.mentconnect.role.model.RoleRepository;
import com.ccsw.mentconnect.role.model.RoleTypeEnum;

/**
 * @author amirzoya
 *
 *         Clase que implementa los métodos de la interfaz RoleService
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    RoleRepository roleRepository;

    public List<RoleEntity> findByType(RoleTypeEnum type) {
        return this.roleRepository.findByType(type);
    }

    public List<RoleEntity> findAll() {
        return roleRepository.findAll();

    }

}
