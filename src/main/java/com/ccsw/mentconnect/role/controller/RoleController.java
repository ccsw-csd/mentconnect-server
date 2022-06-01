package com.ccsw.mentconnect.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.role.dto.RoleDto;
import com.ccsw.mentconnect.role.logic.RoleService;
import com.ccsw.mentconnect.role.model.RoleTypeEnum;

@RequestMapping(value = "/role")
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<RoleDto> findAll() {

        return this.beanMapper.mapList(roleService.findAll(), RoleDto.class);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/findByType/{type}", method = RequestMethod.GET)
    public List<RoleDto> findByType(@PathVariable RoleTypeEnum type) {

        return this.beanMapper.mapList(roleService.findByType(type), RoleDto.class);

    }
}
