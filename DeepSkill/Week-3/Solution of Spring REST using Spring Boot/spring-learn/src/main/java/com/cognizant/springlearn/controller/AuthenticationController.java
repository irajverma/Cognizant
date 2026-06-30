package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("secretkeysecretkeysecretkeysecretkey".getBytes());

    /**
     * GET /authenticate - Authenticates user using Basic Auth header and returns a JWT token.
     * The Authorization header contains "Basic <Base64(user:password)>".
     *
     * @param authHeader the Authorization header value
     * @return Map containing the generated JWT token
     */
    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("START");
        LOGGER.debug("Authorization header: {}", authHeader);

        String user = getUser(authHeader);
        String token = generateJwt(user);

        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);

        LOGGER.debug("Generated token for user {}: {}", user, token);
        LOGGER.info("END");
        return map;
    }

    /**
     * Extracts the username from the Basic Authorization header.
     * Decodes the Base64 encoded credentials and extracts the username before the colon.
     *
     * @param authHeader the Authorization header value (e.g., "Basic dXNlcjpwd2Q=")
     * @return the decoded username
     */
    private String getUser(String authHeader) {
        LOGGER.info("START");

        // Get the Base64 encoded text after "Basic "
        String encodedCredentials = authHeader.substring("Basic ".length());
        LOGGER.debug("Encoded credentials: {}", encodedCredentials);

        // Decode it using Java 8 Base64 API
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedCredentials = new String(decodedBytes);
        LOGGER.debug("Decoded credentials: {}", decodedCredentials);

        // Get the text until colon to get the user
        String user = decodedCredentials.substring(0, decodedCredentials.indexOf(':'));
        LOGGER.debug("User: {}", user);

        LOGGER.info("END");
        return user;
    }

    /**
     * Generates a JWT token for the given user.
     * Token includes:
     * - Subject: the username
     * - Issued at: current time
     * - Expiration: 20 minutes from now
     * - Signed with: HS256 algorithm
     *
     * @param user the username to create the token for
     * @return the compact JWT string
     */
    private String generateJwt(String user) {
        LOGGER.info("START");

        String token = Jwts.builder()
                .setSubject(user)
                // Set the token issue time as current time
                .setIssuedAt(new Date())
                // Set the token expiry as 20 minutes from now
                .setExpiration(new Date((new Date()).getTime() + 1200000))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();

        LOGGER.debug("Generated JWT: {}", token);
        LOGGER.info("END");
        return token;
    }
}
