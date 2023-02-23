package com.ccsw.mentconnect.user.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author amirzoya
 *
 *         Repositorio que accede a la información de la tabla user en base de
 *         datos
 *
 */
public interface UserRepository
        extends PagingAndSortingRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    List<UserEntity> findAll();

    Boolean existsByUsername(String username);
    
    @Query("SELECT u.id FROM UserEntity u where u.username = :username") 
    Long findIdByUsername(@Param("username") String username);

}
