package com.ccsw.mentconnect.security.logic;

import com.ccsw.mentconnect.config.security.JsonWebTokenUtility;
import com.ccsw.mentconnect.role.model.RoleTypeEnum;
import com.ccsw.mentconnect.security.dto.LoginDto;
import com.ccsw.mentconnect.security.dto.SecurityCredencialDto;
import com.ccsw.mentconnect.security.dto.UserDetailsJWTDto;
import com.ccsw.mentconnect.user.logic.UserService;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.userrole.logic.UserRoleService;
import com.ccsw.mentconnect.userrole.model.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author amirzoya
 *
 *         Clase que implementa los métodos de la interfaz SecurityService
 *
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Long EXPIRATION_TIME = 60 * 60 * 1000L;

    @Autowired
    private JsonWebTokenUtility jsonWebTokenUtility;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * Método para realizar Login
     *
     * @return {@link SecurityCredencialDto} credentials
     * @throws BadCredentialsException
     */
    public SecurityCredencialDto login(LoginDto dto) throws BadCredentialsException {

        UserDetailsJWTDto userDetails = new UserDetailsJWTDto();

        Optional<UserEntity> userEntityOptional = userService.autenticate(dto.getUsername(), dto.getPassword());
        if(userEntityOptional.isEmpty()){
            throw new BadCredentialsException("Bad credentials");
        }

        UserEntity user = userEntityOptional.get();
        userDetails.setId(user.getId());
        userDetails.setUsername(user.getUsername());
        userDetails.setName(user.getName());
        userDetails.setSurnames(user.getSurnames());
        userDetails.setEmail(user.getEmail());
        addRoles(userDetails);

        String accessToken = this.jsonWebTokenUtility.createJWT(userDetails, buildExpirationDate(EXPIRATION_TIME));

        SecurityCredencialDto result = new SecurityCredencialDto();
        result.setAccessToken(accessToken);
        result.setExpireTime(EXPIRATION_TIME);

        return result;
    }

    private void addRoles(UserDetailsJWTDto userDetails){
        List<UserRoleEntity> userRoles = userRoleService.findByUser(userDetails.getId());
        userDetails.setRoles(userRoles.stream().map(elem -> elem.getRole().getCode()).collect(Collectors.toList()));

        if(userDetails.getRoles().isEmpty() || userRoles.stream().anyMatch(elem -> RoleTypeEnum.EXT.equals(elem.getRole().getType()))){
            userDetails.addRole("PAT_BACK");
        }
    }

    private Date buildExpirationDate(long expirationSeconds) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, (int) expirationSeconds);
        return calendar.getTime();
    }

}
