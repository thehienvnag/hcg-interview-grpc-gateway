package com.hcg.booking.connects.get_user_details;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.identity.grpc.service.GetUserDetailsReply;
import io.grpc.Channel;

public interface GetUserDetailsService {
    GetUserDetailsReply proceed(String accessToken, Channel channel, Integer userId);
}
