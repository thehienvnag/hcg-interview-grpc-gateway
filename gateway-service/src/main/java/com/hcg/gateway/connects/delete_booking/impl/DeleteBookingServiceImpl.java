package com.hcg.gateway.connects.delete_booking.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.booking.grpc.service.DeleteBookingMessage;
import com.hcg.booking.grpc.service.DeleteBookingServiceGrpc;
import com.hcg.gateway.connects.BaseResponse;
import com.hcg.gateway.connects.delete_booking.DeleteBookingService;
import com.hcg.gateway.connects.delete_booking.dto.DeleteBookingResponse;
import com.hcg.gateway.interceptors.AuthorizationInterceptor;
import com.hcg.gateway.utils.Utils;
import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookingServiceImpl implements DeleteBookingService {

    @Override
    public BaseResponse proceed(String accessToken, Channel channel, String bookingIdStr) throws InvalidProtocolBufferException, JsonProcessingException{

        var interceptedChannel = ClientInterceptors.intercept(channel, new AuthorizationInterceptor(accessToken));
        var stub = DeleteBookingServiceGrpc.newBlockingStub(interceptedChannel);

        DeleteBookingMessage message = DeleteBookingMessage.newBuilder()
                .setBookingId(Integer.parseInt(bookingIdStr))
                .build();

        return Utils.toObject(stub.delete(message), DeleteBookingResponse.class);
    }
}
