package com.ccsw.mentconnect.user.logic;

import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.user.dto.UserDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;
import com.ccsw.mentconnect.user.model.UserEntity;
import org.springframework.data.domain.Page;

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
    
    UserEntity saveUser (UserDto userDto) throws AlreadyExistsException;
    
    UserEntity modifyUser(UserDto userDto) throws EntityNotFoundException;

}
