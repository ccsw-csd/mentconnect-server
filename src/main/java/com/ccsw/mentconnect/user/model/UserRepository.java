package com.ccsw.mentconnect.user.model;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.mentconnect.user.dto.UserSearchDto;

import java.util.List;
import java.util.Optional;

/**
 * @author amirzoya
 *
 *         Repositorio que accede a la información de la tabla user en base de datos
 *
 */
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);


    @Override
    List<UserEntity> findAll();
    
   
   // void S(UserEntity dto);
   // void update(Long id, UserEntity dto);

}
