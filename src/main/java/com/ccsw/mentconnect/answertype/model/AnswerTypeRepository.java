package com.ccsw.mentconnect.answertype.model;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AnswerTypeRepository extends PagingAndSortingRepository<AnswerTypeEntity, Long> {

    List<AnswerTypeEntity> findByDescription(String description);
}
