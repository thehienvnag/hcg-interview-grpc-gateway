package com.hcg.booking.grpc.create_booking;

import com.hcg.booking.grpc.ActionTrigger;
import com.hcg.booking.grpc.service.CreateBookingMessage;
import com.hcg.booking.grpc.service.CreateBookingReply;
import com.hcg.booking.grpc.service.CreateBookingServiceGrpc;
import com.hcg.booking.interceptors.UserTokenValidationInterceptor;
import com.hcg.booking.mapper.MessageMapper;
import com.hcg.booking.message.ValidatedCreateBookingMessage;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService(interceptors = {UserTokenValidationInterceptor.class})
@RequiredArgsConstructor
public class CreateBookingServiceGrpcImpl extends CreateBookingServiceGrpc.CreateBookingServiceImplBase {

    private final CreateBookingAction action;

    private final ActionTrigger<ValidatedCreateBookingMessage, CreateBookingReply> actionTrigger;

    @Override
    public void create(CreateBookingMessage request, StreamObserver<CreateBookingReply> responseObserver) {
        var validatedMsg = MessageMapper.INSTANCE.convertToValidatedCreateBookingMessage(request);
        actionTrigger
                .init(action) // Init action for later trigger
                .proceed(validatedMsg, responseObserver);
    }
}
