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
 *
 */
public interface UserRepository
        extends PagingAndSortingRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    // public Page<User> findAll(Specification<User> spec, Pageable pageable);
    @Override
    // public List<UserEntity> findAll(Specification<UserEntity> spec);

    List<UserEntity> findAll();

    // void S(UserEntity dto);
    // void update(Long id, UserEntity dto);

}
