package com.ccsw.mentconnect.user.logic;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.mentconnect.common.criteria.SearchCriteria;
import com.ccsw.mentconnect.common.exception.AlreadyExistsException;
import com.ccsw.mentconnect.common.exception.EntityNotFoundException;
import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.role.model.RoleEntity;
import com.ccsw.mentconnect.user.dto.UserFullDto;
import com.ccsw.mentconnect.user.dto.UserSearchDto;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;

/**
 * @author amirzoya
 *
 *         Clase que implementa los métodos de la interfaz UserService
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${user.password.length}")
    private Integer length;

    @Value("${user.password.chars}")
    private String chars;

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<UserEntity> autenticate(String username, String password) {

        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public UserEntity get(Long id) throws EntityNotFoundException {

        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserEntity saveUser(UserFullDto userDto) throws AlreadyExistsException {

        if (this.userRepository.existsByUsername(userDto.getUsername())) {
            throw new AlreadyExistsException();
        }

        UserEntity userEntity = this.beanMapper.map(userDto, UserEntity.class);
        String password = this.generatePassword();
        System.out.println("Contraseña generada: " + password); // TODO borrar cuando se envie el email.
        userEntity.setPassword(this.encryptSha256(password));

        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity modifyUser(UserFullDto userDto) throws EntityNotFoundException {

        if (userDto.getId() == null) {
            throw new EntityNotFoundException();
        }

        UserEntity updateUser = this.get(userDto.getId());
        updateUser.setName(userDto.getName());
        updateUser.setSurnames(userDto.getSurnames());
        updateUser.setEmail(userDto.getEmail());
        updateUser.setRoles(this.beanMapper.mapList(userDto.getRoles(), RoleEntity.class));

        return this.userRepository.save(updateUser);
    }

    @Override
    public List<UserEntity> findAll() {

        return userRepository.findAll();
    }

    @Override
    public Page<UserEntity> findPage(UserSearchDto dto) {

        UserSpecification id = new UserSpecification(new SearchCriteria(UserEntity.ATT_ID, ":", dto.getId()));
        UserSpecification username = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_USERNAME, ":", dto.getUsername()));
        UserSpecification name = new UserSpecification(new SearchCriteria(UserEntity.ATT_NAME, ":", dto.getName()));
        UserSpecification surnames = new UserSpecification(
                new SearchCriteria(UserEntity.ATT_SURNAMES, ":", dto.getSurnames()));
        UserSpecification email = new UserSpecification(new SearchCriteria(UserEntity.ATT_EMAIL, ":", dto.getEmail()));

        Specification<UserEntity> spec = Specification.where(id).and(username).and(name).and(surnames).and(email);

        return userRepository.findAll(spec, dto.getPageable());
    }

    private String generatePassword() {
        return RandomStringUtils.random(length, chars);
    }

    private String encryptSha256(String password) {
        return DigestUtils.sha256Hex(password);
    }

}
