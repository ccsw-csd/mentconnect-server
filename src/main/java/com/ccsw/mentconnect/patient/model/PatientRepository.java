package com.ccsw.mentconnect.patient.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PatientRepository extends PagingAndSortingRepository<PatientEntity, Long>, JpaSpecificationExecutor<PatientEntity> {

    @Override
    @EntityGraph(attributePaths = {"user"})
    List<PatientEntity> findAll();

    @Override
    @EntityGraph(attributePaths = {"user"})
    Page<PatientEntity> findAll(Specification spec, Pageable pageable);

    boolean existsByNif(String nif);
}