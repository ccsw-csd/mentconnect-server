package com.ccsw.mentconnect.config.security;

import com.ccsw.mentconnect.security.dto.UserDetailsJWTDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * Can process a specific {@link Authentication} implementation.
 *
 */
@Component
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JsonWebTokenUtility jwtUtility;

    /**
     * {@inheritDoc}
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Authentication authenticatedUser = null;

        String tokenHeader = (String) authentication.getPrincipal();

        tokenHeader = tokenHeader.substring(tokenHeader.indexOf(' ') + 1);

        UserDetailsJWTDto details = this.jwtUtility.createUserDetails(tokenHeader);
        if (details != null)
          authenticatedUser = new JsonWebTokenAuthentication(details, tokenHeader);

        return authenticatedUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
            || authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
    }

}
