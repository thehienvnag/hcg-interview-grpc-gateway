package com.hcg.booking.grpc.delete_booking;

import com.hcg.booking.grpc.ActionTrigger;
import com.hcg.booking.grpc.service.DeleteBookingMessage;
import com.hcg.booking.grpc.service.DeleteBookingReply;
import com.hcg.booking.grpc.service.DeleteBookingServiceGrpc;
import com.hcg.booking.interceptors.UserTokenValidationInterceptor;
import com.hcg.booking.mapper.MessageMapper;
import com.hcg.booking.message.ValidatedDeleteBookingMessage;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService(interceptors = {UserTokenValidationInterceptor.class})
@RequiredArgsConstructor
public class DeleteBookingServiceGrpcImpl extends DeleteBookingServiceGrpc.DeleteBookingServiceImplBase {

    private final DeleteBookingAction action;

    private final ActionTrigger<ValidatedDeleteBookingMessage, DeleteBookingReply> actionTrigger;

    @Override
    public void delete(DeleteBookingMessage request, StreamObserver<DeleteBookingReply> responseObserver) {
        var validatedMsg = MessageMapper.INSTANCE.convertToValidatedDeleteBookingMessage(request);
        actionTrigger
                .init(action) // Init action for later trigger
                .proceed(validatedMsg, responseObserver);
    }

}
