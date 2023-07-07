package com.hcg.gateway.connects.create_booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.gateway.connects.BaseResponse;
import com.hcg.gateway.connects.create_booking.dto.CreateBookingRequest;
import io.grpc.Channel;

public interface CreateBookingService {
    BaseResponse proceed(String accessToken, Channel channel, CreateBookingRequest request) throws InvalidProtocolBufferException, JsonProcessingException;
}
