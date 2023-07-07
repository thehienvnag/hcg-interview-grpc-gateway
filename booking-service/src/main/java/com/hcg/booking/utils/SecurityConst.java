package com.hcg.booking.utils;

import io.grpc.Metadata;

public class SecurityConst {
    public static final Metadata.Key<String> AUTHORIZATION_KEY =  Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);
}
