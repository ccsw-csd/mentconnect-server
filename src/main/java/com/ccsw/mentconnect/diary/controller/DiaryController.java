package com.ccsw.mentconnect.diary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.diary.dto.DateSearchDiaryDto;
import com.ccsw.mentconnect.diary.dto.DiaryDto;
import com.ccsw.mentconnect.diary.logic.DiaryService;

@RequestMapping(value = "/diary")
@RestController
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    BeanMapper beanMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/filter", method = RequestMethod.POST)
    public List<DiaryDto> getDiaryByPatientId(@RequestBody DateSearchDiaryDto date) throws EntityNotFoundException {
        return this.beanMapper.mapList(diaryService.getDiaryByPatientId(date), DiaryDto.class);
    }
}
