package com.hcg.booking.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hcg.booking.configuration.JwtProperties;
import com.hcg.booking.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;
    @Override
    public DecodedJWT validateToken(String token) {
        try {
            return isTokenExpired(token);
        } catch (JWTVerificationException ex) {
            return null;
        }
    }


    private DecodedJWT isTokenExpired(String token) throws JWTVerificationException {

        final Date expirationDateFromToken = getExpirationDateFromToken(token);
        if (expirationDateFromToken.before(new Date())) {
            throw new JWTVerificationException("Token is expired");
        } else {
            return getDecodedJWT(token);
        }
    }

    private Date getExpirationDateFromToken(String token) throws JWTVerificationException {

        final DecodedJWT decodedJWT = getDecodedJWT(token);
        return decodedJWT.getExpiresAt();
    }

    private DecodedJWT getDecodedJWT(String token) throws JWTVerificationException {

        final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes())).build();
        return jwtVerifier.verify(token);
    }
}
