package com.hcg.gateway.connects.register.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.gateway.connects.BaseResponse;
import com.hcg.gateway.connects.register.RegisterService;
import com.hcg.gateway.connects.register.dto.RegisterRequest;
import com.hcg.gateway.connects.register.dto.RegisterResponse;
import com.hcg.gateway.utils.Utils;
import com.hcg.identity.grpc.service.RegisterMessage;
import com.hcg.identity.grpc.service.RegisterServiceGrpc;
import io.grpc.Channel;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Override
    public BaseResponse proceed(Channel channel, RegisterRequest request) throws InvalidProtocolBufferException, JsonProcessingException {
        var stub = RegisterServiceGrpc.newBlockingStub(channel);

        RegisterMessage message = RegisterMessage.newBuilder()
                .setEmail(request.getEmail())
                .setPassword(request.getPassword())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .build();

        return Utils.toObject(stub.register(message), RegisterResponse.class);
    }
}
