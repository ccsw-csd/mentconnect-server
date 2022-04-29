package com.capgemini.mentconnect.security.controller;

import com.capgemini.mentconnect.security.dto.LoginDto;
import com.capgemini.mentconnect.security.dto.SecurityCredencialDto;
import com.capgemini.mentconnect.security.logic.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/security")
@RestController
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public SecurityCredencialDto login(@RequestBody LoginDto dto) throws BadCredentialsException {

        return securityService.login(dto);
    }

}
