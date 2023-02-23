package com.ccsw.mentconnect.user.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.user.dto.UserFullDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;
import com.ccsw.mentconnect.user.model.UserEntity;

/**
 * @author amirzoya
 *
 *         Interfaz que declara las operaciones de negocio de la entidad User
 *
 */
public interface UserService {

    Optional<UserEntity> autenticate(String username, String password);

    UserEntity get(Long id) throws EntityNotFoundException;

    UserEntity saveUser(UserFullDto userDto) throws AlreadyExistsException;

    UserEntity modifyUser(UserFullDto userDto) throws EntityNotFoundException;

    List<UserEntity> findAll();

    Page<UserEntity> findPage(UserSearchDto dto);

    List<UserEntity> findFilter(String filter);
    
    Long getUserIdByUsername(String username);

}
