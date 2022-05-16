package com.ccsw.mentconnect.user.controller;

import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;
import com.ccsw.mentconnect.user.logic.UserService;
import com.ccsw.mentconnect.user.model.UserEntity;
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
   
   /* @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public void save (@RequestBody UserDto dto){
    	this.userService.save(dto);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/update{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Long id, @RequestBody UserDto dto) {
    	this.userService.update(id, dto);
    }*/
/* 
    @RequestMapping(path ="/find", method = RequestMethod.POST)
    public Page<UserDto> find(@RequestParam(value="id", required = false)Long id, @RequestParam(value="name",required = false) String name,
    @RequestParam(value="username", required = false) String username, @RequestParam(value="surnames", required = false)String surnames,
    @RequestParam(value="email", required = false) String email, @RequestBody UserSearchDto dto){
	
    	Page<UserEntity> response = userService.find(id, username, surnames, email);
        return new PageImpl<>(this.mapperFacade.mapAsList(response.getContent(), UserDto.class), response.getPageable(), response.getTotalElements());
    	
    }*/
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/findFilterPage", method = RequestMethod.POST)
    public Page<UserDto> findFilterPage(@RequestBody UserSearchDto dto) {

        Page<UserEntity> response = userService.findFilterPage(dto);

        return new PageImpl<>(this.mapperFacade.mapAsList(response.getContent(), UserDto.class), response.getPageable(), response.getTotalElements());
    }
}
