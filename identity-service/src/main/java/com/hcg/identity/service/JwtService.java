package com.hcg.identity.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hcg.identity.dto.AuthenticatedUserDto;

public interface JwtService {
    String generateToken(AuthenticatedUserDto user);
    DecodedJWT validateToken(String token);
}
