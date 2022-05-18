package com.ccsw.mentconnect.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class BaseITAbstract {

    private static final String CLAIM_ID = "id";

    private static final String CLAIM_USERNAME = "username";

    private static final String CLAIM_NAME = "name";

    private static final String CLAIM_SURNAMES = "surnames";

    private static final String CLAIM_EMAIL = "email";

    private static final String CLAIM_ROLES = "roleCode";

    protected static final String LOCALHOST = "http://localhost:";

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    private static final Long EXPIRATION_TIME = 60 * 60 * 1000L;

    private static final String USER = "user";

    @Value("${jwt.encodedKey}")
    private String encodedKey;

    @Autowired
    protected TestRestTemplate restTemplate;

    @LocalServerPort
    protected int port;

    protected HttpHeaders getHeaders(){

        byte[] decodedKey = DatatypeConverter.parseBase64Binary(encodedKey);
        Key secretKey = new SecretKeySpec(decodedKey, SIGNATURE_ALGORITHM.getJcaName());

        JwtBuilder jBuilder = Jwts.builder().setSubject(USER).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SIGNATURE_ALGORITHM, secretKey);

        addCustomPropertiesUserDetailsToJwt(jBuilder);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jBuilder.compact());

        return headers;
    }

    private void addCustomPropertiesUserDetailsToJwt(JwtBuilder jBuilder) {

        jBuilder.claim(CLAIM_ROLES, List.of("ADMIN"));
        jBuilder.claim(CLAIM_USERNAME, USER);
        jBuilder.claim(CLAIM_ID, -1);
        jBuilder.claim(CLAIM_NAME, USER);
        jBuilder.claim(CLAIM_SURNAMES, USER);
        jBuilder.claim(CLAIM_EMAIL, USER);
    }
}
