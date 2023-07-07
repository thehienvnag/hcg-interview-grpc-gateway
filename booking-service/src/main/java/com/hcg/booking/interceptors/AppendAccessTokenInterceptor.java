package com.hcg.booking.interceptors;

import com.hcg.booking.utils.SecurityConst;
import io.grpc.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppendAccessTokenInterceptor implements ClientInterceptor {

    private final String token;

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method,
                                                               CallOptions callOptions, Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
                next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(SecurityConst.AUTHORIZATION_KEY, token);
                super.start(responseListener, headers);
            }
        };
    }
}
