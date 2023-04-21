package com.ccsw.mentconnect.diary.model;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DiaryRepository extends PagingAndSortingRepository<DiaryEntity, Long>, JpaSpecificationExecutor<DiaryEntity> {
    List<DiaryEntity> findByPatientId(Long patientId, Sort sortByDate);
}
