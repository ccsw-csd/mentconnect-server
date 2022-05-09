package com.ccsw.mentconnect.security.logic;

import com.ccsw.mentconnect.security.dto.LoginDto;
import com.ccsw.mentconnect.security.dto.SecurityCredencialDto;

/**
 * @author amirzoya
 *
 *         Interfaz que declara las operaciones de negocio de la parte de seguridad
 *
 */
public interface SecurityService {

    SecurityCredencialDto login(LoginDto dto);

}
