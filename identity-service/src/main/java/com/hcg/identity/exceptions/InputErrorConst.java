package com.hcg.identity.exceptions;

import io.grpc.Metadata;

public class InputErrorConst {
    public static final Metadata.Key<String> KEY = Metadata.Key.of("ERRORS", Metadata.ASCII_STRING_MARSHALLER);
}
