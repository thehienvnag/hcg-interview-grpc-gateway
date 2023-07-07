package com.hcg.gateway.connects.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.gateway.connects.BaseResponse;
import com.hcg.gateway.connects.login.dto.LoginRequest;
import io.grpc.Channel;

public interface LoginService {
    BaseResponse proceed(Channel channel, LoginRequest request) throws InvalidProtocolBufferException, JsonProcessingException;
}
