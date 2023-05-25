package com.ccsw.mentconnect.weekday.logic;

import java.util.List;
import com.ccsw.mentconnect.weekday.model.WeekDayEntity;

public interface WeekDayService {
    List<WeekDayEntity> findAll();
}