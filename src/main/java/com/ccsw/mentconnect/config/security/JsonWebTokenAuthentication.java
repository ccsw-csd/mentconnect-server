package com.ccsw.mentconnect.config.security;

import com.ccsw.mentconnect.security.dto.UserDetailsJWTDto;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * An authentication object
 *
 */
public class JsonWebTokenAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String principal;

    /**
     * The constructor.
     *
     * @param details      the {@link UserDetailsJWTDto} containing additional user
     *                     details about the authentication request
     * @param jsonWebToken the web token JSON as {@link String}
     *
     */
    public JsonWebTokenAuthentication(UserDetailsJWTDto details, String jsonWebToken) {

        super(details.getAuthorities());
        super.setDetails(details);
        setPrincipal(jsonWebToken);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getCredentials() {

        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPrincipal() {

        return this.principal;
    }

    /**
     * @param principal new value of {@link #getPrincipal}.
     */
    public void setPrincipal(String principal) {

        this.principal = principal;
    }

}
