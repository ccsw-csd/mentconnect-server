package com.capgemini.mentconnect.user.controller;

import com.capgemini.mentconnect.common.exception.EntityNotFoundException;
import com.capgemini.mentconnect.user.dto.UserDto;
import com.capgemini.mentconnect.user.dto.UserSearchDto;
import com.capgemini.mentconnect.user.logic.UserService;
import com.capgemini.mentconnect.user.model.UserEntity;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MapperFacade mapperFacade;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public UserDto get(@PathVariable Long id) throws EntityNotFoundException {

        return this.mapperFacade.map(userService.get(id), UserDto.class);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<UserDto> findAll() {

        return this.mapperFacade.mapAsList(userService.findAll(), UserDto.class);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/findPage", method = RequestMethod.POST)
    public Page<UserDto> findPage(@RequestBody UserSearchDto dto) {

        Page<UserEntity> response = userService.findPage(dto);

        return new PageImpl<>(this.mapperFacade.mapAsList(response.getContent(), UserDto.class), response.getPageable(), response.getTotalElements());
    }

}
