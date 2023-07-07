package com.hcg.identity.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class BearerTokenAuthenticationToken extends AbstractAuthenticationToken {
    private final String token;

    public BearerTokenAuthenticationToken(String token) {
        super(null);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
