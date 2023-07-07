package com.hcg.gateway.connects.register;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.gateway.connects.BaseResponse;
import com.hcg.gateway.connects.register.dto.RegisterRequest;
import io.grpc.Channel;

public interface RegisterService {
    BaseResponse proceed(Channel channel, RegisterRequest request) throws InvalidProtocolBufferException, JsonProcessingException;
}
