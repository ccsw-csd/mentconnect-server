package com.ccsw.mentconnect.config.security;

import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.ccsw.mentconnect.security.dto.UserDetailsJWTDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

/**
 * @author amirzoya
 */
@Component
public class JsonWebTokenUtility {

    private static final String CLAIM_ID = "id";

    private static final String CLAIM_USERNAME = "username";

    private static final String CLAIM_NAME = "name";

    private static final String CLAIM_SURNAMES = "surnames";

    private static final String CLAIM_EMAIL = "email";

    private static final String CLAIM_ROLES = "roleCode";

    private static final Logger LOG = LoggerFactory.getLogger(JsonWebTokenUtility.class);

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    private Key secretKey;

    /**
     * Create JWT from UserDetails
     *
     * @param userDetails The {@link UserDetailsJWTDto user} to create the token
     * @return The JWT token
     */
    public final String createJWT(UserDetailsJWTDto userDetails, Date expirationDate) {

        JwtBuilder jBuilder = Jwts.builder().setSubject(userDetails.getUsername()).setExpiration(expirationDate).signWith(SIGNATURE_ALGORITHM, this.secretKey);

        addCustomPropertiesUserDetailsToJwt(userDetails, jBuilder);

        return jBuilder.compact();
    }

    /**
     * Create UserDetails from JWT
     *
     * @param jwtToken The json web token
     * @return userDetails
     */
    public final UserDetailsJWTDto createUserDetails(String jwtToken) {

        try {
            UserDetailsJWTDto userDetails = new UserDetailsJWTDto();
            Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(jwtToken).getBody();

            if (isExpired(claims))
              return null;

            userDetails.setUsername(claims.getSubject());

            addCustomPropertiesJwtToUserDetails(claims, userDetails);

            return userDetails;

        } catch (Exception ex) {
            LOG.info("User token not valid " + jwtToken);
            return null;
        }
    }

    /**
     * @param claims claims
     */
    private boolean isExpired(Claims claims) {

        Date expirationDate = claims.getExpiration();
        Date now = new Date();

        return now.after(expirationDate);
    }

    /**
     * Add a custom properties from UserDetailsJWTDto to JWT
     *
     * @param userDetails userDetails
     * @param jBuilder userDetails
     */
    private void addCustomPropertiesUserDetailsToJwt(UserDetailsJWTDto userDetails, JwtBuilder jBuilder) {

        jBuilder.claim(CLAIM_ROLES, userDetails.getRoles());
        jBuilder.claim(CLAIM_ID, userDetails.getId());
        jBuilder.claim(CLAIM_USERNAME, userDetails.getUsername());
        jBuilder.claim(CLAIM_NAME, userDetails.getName());
        jBuilder.claim(CLAIM_SURNAMES, userDetails.getSurnames());
        jBuilder.claim(CLAIM_EMAIL, userDetails.getEmail());
    }

    /**
     * Add a custom properties from JWT to UserDetailsJWTDto
     *
     * @param claims claims
     * @param userDetails user details
     */
    private void addCustomPropertiesJwtToUserDetails(Claims claims, UserDetailsJWTDto userDetails) {

        if (claims.get(CLAIM_ROLES) != null) {
          userDetails.setRoles((List<String>) claims.get(CLAIM_ROLES));
        }
        userDetails.setId(Long.valueOf((Integer) claims.get(CLAIM_ID)));
        userDetails.setUsername((String) claims.get(CLAIM_USERNAME));
        userDetails.setName((String) claims.get(CLAIM_NAME));
        userDetails.setSurnames((String) claims.get(CLAIM_SURNAMES));
        userDetails.setEmail((String) claims.get(CLAIM_EMAIL));
    }

    /**
     * Set the encodedKey from properties
     *
     * @param encodedKey new value of encoded key
     */
    @Value("${jwt.encodedKey}")
    public final void setEncodedKey(String encodedKey) {

        byte[] decodedKey = DatatypeConverter.parseBase64Binary(encodedKey);
        this.secretKey = new SecretKeySpec(decodedKey, SIGNATURE_ALGORITHM.getJcaName());
    }

}