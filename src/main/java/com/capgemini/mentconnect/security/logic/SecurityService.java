package com.capgemini.mentconnect.security.logic;

import com.capgemini.mentconnect.security.dto.LoginDto;
import com.capgemini.mentconnect.security.dto.SecurityCredencialDto;

/**
 * @author amirzoya
 *
 *         Interfaz que declara las operaciones de negocio de la parte de seguridad
 *
 */
public interface SecurityService {

    SecurityCredencialDto login(LoginDto dto);

}
