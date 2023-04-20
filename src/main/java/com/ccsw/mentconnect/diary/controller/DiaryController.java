package com.ccsw.mentconnect.diary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.diary.dto.DateSearchDiaryDto;
import com.ccsw.mentconnect.diary.dto.DiaryDto;
import com.ccsw.mentconnect.diary.dto.DiarySimpleDto;
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
    public List<DiarySimpleDto> getDiaryByPatientId(@RequestBody DateSearchDiaryDto date){
        return this.beanMapper.mapList(diaryService.getDiaryByPatientId(date), DiarySimpleDto.class);
    }
    
    //save, delete y edit con DiaryDto
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public DiaryDto saveDiary(@RequestBody DiaryDto diaryDto) throws AlreadyExistsException{

        return this.beanMapper.map(diaryService.saveDiary(diaryDto), DiaryDto.class);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public DiaryDto modifyDiary(@RequestBody DiaryDto diaryDto) throws EntityNotFoundException {
        
        return this.beanMapper.map(diaryService.modifyDiary(diaryDto), DiaryDto.class);
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteDiary(@PathVariable Long id) {
        this.diaryService.delete(id);
    }
}
