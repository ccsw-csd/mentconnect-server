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
import com.ccsw.mentconnect.diary.dto.DiarySimpleDto;
import com.ccsw.mentconnect.diary.logic.DiaryService;
import com.ccsw.mentconnect.diary.model.DiaryEntity;

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
    
    
    @PreAuthorize("hasAnyAuthority('PAT_DAILY')")
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public DiaryDto saveOrUpdateDiary(@RequestBody DiaryDto diaryDto) throws EntityNotFoundException {
        return this.beanMapper.map(diaryService.saveOrUpdateDiary(this.beanMapper.map(diaryDto,DiaryEntity.class)), DiaryDto.class);
    }
    
    @PreAuthorize("hasAnyAuthority('PAT_DAILY')")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteDiary(@PathVariable Long id) {
        this.diaryService.delete(id);
    }
}
