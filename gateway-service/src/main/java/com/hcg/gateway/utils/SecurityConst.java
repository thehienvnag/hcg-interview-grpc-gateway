package com.hcg.gateway.utils;

import io.grpc.Metadata;

public class SecurityConst {
    public static final Metadata.Key<String> AUTHORIZATION_KEY =  Metadata.Key.of("Authorization", io.grpc.Metadata.ASCII_STRING_MARSHALLER);
}
