package com.ccsw.mentconnect.user.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author amirzoya
 *
 *         Repositorio que accede a la información de la tabla user en base de
 *         datos
 * @param <UserDto>
 *
 */
public interface UserRepository
        extends PagingAndSortingRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

//    Page<UserDto> findPage(Pageable pageable);

    List<UserEntity> findAll();
    
    boolean existsByUsername(String username);

}
