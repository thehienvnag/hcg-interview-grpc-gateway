package com.hcg.gateway.interceptors;

import com.hcg.gateway.utils.SecurityConst;
import io.grpc.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationInterceptor implements ClientInterceptor {

    private final String token;

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method,
                                                               CallOptions callOptions, Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
                next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                if (token != null) {
                    headers.put(SecurityConst.AUTHORIZATION_KEY, token);
                }
                super.start(responseListener, headers);
            }
        };
    }
}
