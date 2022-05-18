package com.ccsw.mentconnect.user.model;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author amirzoya
 *
 *         Repositorio que accede a la información de la tabla user en base de datos
 *
 */
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    @Override
    List<UserEntity> findAll();
    
    boolean existsByUsername(String username);

}
