package com.hcg.gateway.connects.login.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.gateway.connects.BaseResponse;
import com.hcg.gateway.connects.login.LoginService;
import com.hcg.gateway.connects.login.dto.LoginRequest;
import com.hcg.gateway.connects.login.dto.LoginResponse;
import com.hcg.gateway.utils.Utils;
import com.hcg.identity.grpc.service.LoginMessage;
import com.hcg.identity.grpc.service.LoginServiceGrpc;
import io.grpc.Channel;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public BaseResponse proceed(Channel channel, LoginRequest request) throws InvalidProtocolBufferException, JsonProcessingException {
        var stub = LoginServiceGrpc.newBlockingStub(channel);

        LoginMessage message = LoginMessage.newBuilder()
                .setEmail(request.getEmail())
                .setPassword(request.getPassword())
                .build();

        return Utils.toObject(stub.login(message), LoginResponse.class);
    }

}
