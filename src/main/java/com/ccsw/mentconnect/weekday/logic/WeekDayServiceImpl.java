package com.ccsw.mentconnect.weekday.logic;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.diary.model.DiaryEntity;
import com.ccsw.mentconnect.weekday.model.WeekDayEntity;
import com.ccsw.mentconnect.weekday.model.WeekDayRepository;

@Service
public class WeekDayServiceImpl implements WeekDayService {

    @Autowired
    WeekDayRepository weekDayRepository;

    public List<WeekDayEntity> findAll() {
        Sort sortById = Sort.by(Sort.Direction.ASC, "id"); 
        return weekDayRepository.findAll(sortById);
    }

}