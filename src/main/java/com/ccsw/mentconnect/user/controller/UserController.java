package com.ccsw.mentconnect.user.controller;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;
import com.ccsw.mentconnect.user.logic.UserService;

import com.ccsw.mentconnect.user.model.UserEntity;
import com.devonfw.module.beanmapping.common.api.BeanMapper;

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

        Page<UserEntity> response = userService.findPage(dto);

        return new PageImpl<>(this.beanMapper.mapList(response.getContent(), UserDto.class), response.getPageable(),
                response.getTotalElements());
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public UserDto saveUser(@RequestBody UserDto userDto) throws AlreadyExistsException{
      
      return this.beanMapper.map(userService.saveUser(userDto), UserDto.class);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public UserDto modifyUser(@RequestBody UserDto userDto) throws EntityNotFoundException{
      
      return this.beanMapper.map(userService.modifyUser(userDto), UserDto.class);
    }
    
    
    

}
