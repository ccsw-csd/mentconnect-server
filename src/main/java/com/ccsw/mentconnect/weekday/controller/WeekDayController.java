package com.ccsw.mentconnect.weekday.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.weekday.dto.WeekDayDto;
import com.ccsw.mentconnect.weekday.logic.WeekDayService;

@RequestMapping(value = "/weekday")
@RestController
public class WeekDayController {

    @Autowired
    WeekDayService weekDayService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<WeekDayDto> findAll() {

        return this.beanMapper.mapList(weekDayService.findAll(), WeekDayDto.class);
    }
}
