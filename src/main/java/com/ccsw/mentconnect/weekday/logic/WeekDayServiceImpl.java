package com.ccsw.mentconnect.weekday.logic;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccsw.mentconnect.weekday.model.WeekDayEntity;
import com.ccsw.mentconnect.weekday.model.WeekDayRepository;

@Service
public class WeekDayServiceImpl implements WeekDayService {

    @Autowired
    WeekDayRepository weekDayRepository;

    public List<WeekDayEntity> findAll() {

        return weekDayRepository.findAll();
    }

}
