package com.hcg.gateway.connects.create_booking.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.booking.grpc.service.CreateBookingMessage;
import com.hcg.booking.grpc.service.CreateBookingServiceGrpc;
import com.hcg.gateway.connects.BaseResponse;
import com.hcg.gateway.connects.create_booking.CreateBookingService;
import com.hcg.gateway.connects.create_booking.dto.CreateBookingRequest;
import com.hcg.gateway.connects.create_booking.dto.CreateBookingResponse;
import com.hcg.gateway.interceptors.AuthorizationInterceptor;
import com.hcg.gateway.utils.Utils;
import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import org.springframework.stereotype.Service;

@Service
public class CreateBookingServiceImpl implements CreateBookingService {

    @Override
    public BaseResponse proceed(String accessToken, Channel channel, CreateBookingRequest request) throws InvalidProtocolBufferException, JsonProcessingException {
        var interceptedChannel = ClientInterceptors.intercept(channel, new AuthorizationInterceptor(accessToken));
        var stub = CreateBookingServiceGrpc.newBlockingStub(interceptedChannel);

        CreateBookingMessage message = CreateBookingMessage.newBuilder()
                .setRoomId(request.getRoomId())
                .setGuestId(request.getGuestId())
                .setCheckinTime(request.getCheckinTime())
                .setCheckoutTime(request.getCheckoutTime())
                .build();

        return Utils.toObject(stub.create(message), CreateBookingResponse.class);
    }
}
