package com.ccsw.mentconnect.user.controller;

import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;
import com.ccsw.mentconnect.user.logic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public UserDto get(@PathVariable Long id) throws EntityNotFoundException {

        return this.beanMapper.map(userService.get(id), UserDto.class);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<UserDto> findAll() {

        return this.beanMapper.mapList(userService.findAll(), UserDto.class);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/findPage", method = RequestMethod.POST)
    public Page<UserDto> findPage(@RequestBody UserSearchDto dto) {

        return this.beanMapper.mapPage(userService.findPage(dto), UserDto.class);
    }

}
