package com.ccsw.mentconnect.patient.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientRepository extends PagingAndSortingRepository<PatientEntity, Long>, JpaSpecificationExecutor<PatientEntity>{
	List<PatientEntity> findAll();

    boolean existsByNif(String nif);
}
