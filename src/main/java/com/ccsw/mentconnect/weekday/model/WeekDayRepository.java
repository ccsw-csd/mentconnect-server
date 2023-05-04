package com.ccsw.mentconnect.weekday.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WeekDayRepository extends CrudRepository<WeekDayEntity, Long> {
    List<WeekDayEntity> findAll();
}
