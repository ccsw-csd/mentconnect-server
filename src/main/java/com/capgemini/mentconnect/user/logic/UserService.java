package com.capgemini.mentconnect.user.logic;

import com.capgemini.mentconnect.common.exception.EntityNotFoundException;
import com.capgemini.mentconnect.user.dto.UserSearchDto;
import com.capgemini.mentconnect.user.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author amirzoya
 *
 *         Interfaz que declara las operaciones de negocio de la entidad User
 *
 */
public interface UserService {

    Optional<UserEntity> autenticate(String username, String password);

    UserEntity get(Long id) throws EntityNotFoundException;

    List<UserEntity> findAll();

    Page<UserEntity> findPage(UserSearchDto dto);

}
