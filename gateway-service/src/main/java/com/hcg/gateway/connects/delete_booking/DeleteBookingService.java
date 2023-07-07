package com.hcg.gateway.connects.delete_booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.gateway.connects.BaseResponse;
import io.grpc.Channel;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface DeleteBookingService {
    BaseResponse proceed(String accessToken, Channel channel, String bookingId) throws InvalidProtocolBufferException, JsonProcessingException;
}
