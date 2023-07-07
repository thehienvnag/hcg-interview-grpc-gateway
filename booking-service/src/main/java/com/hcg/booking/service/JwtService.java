package com.hcg.booking.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtService {
    DecodedJWT validateToken(String token);
}
