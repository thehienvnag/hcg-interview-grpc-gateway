package com.hcg.booking.connects.get_user_details.impl;

import com.hcg.booking.connects.get_user_details.GetUserDetailsService;
import com.hcg.booking.interceptors.AppendAccessTokenInterceptor;
import com.hcg.identity.grpc.service.GetUserDetailsMessage;
import com.hcg.identity.grpc.service.GetUserDetailsReply;
import com.hcg.identity.grpc.service.GetUserDetailsServiceGrpc;
import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import org.springframework.stereotype.Service;

@Service
public class GetUserDetailsServiceImpl implements GetUserDetailsService {

    @Override
    public GetUserDetailsReply proceed(String accessToken, Channel channel, Integer userId) {
        var interceptedChannel = ClientInterceptors.intercept(channel, new AppendAccessTokenInterceptor(accessToken));
        var stub = GetUserDetailsServiceGrpc.newBlockingStub(interceptedChannel);

        GetUserDetailsMessage message = GetUserDetailsMessage.newBuilder()
                .setUserId(userId)
                .build();

        return stub.getDetails(message);

    }
}
