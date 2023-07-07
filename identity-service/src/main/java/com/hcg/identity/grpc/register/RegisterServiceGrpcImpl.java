package com.hcg.identity.grpc.register;

import com.hcg.identity.grpc.ActionTrigger;
import com.hcg.identity.grpc.service.RegisterMessage;
import com.hcg.identity.grpc.service.RegisterReply;
import com.hcg.identity.grpc.service.RegisterServiceGrpc;
import com.hcg.identity.mapper.MessageMapper;
import com.hcg.identity.message.ValidatedRegisterMessage;
import com.hcg.identity.service.JwtService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
@RequiredArgsConstructor
public class RegisterServiceGrpcImpl extends RegisterServiceGrpc.RegisterServiceImplBase {

    private final RegisterAction action;

    private final ActionTrigger<ValidatedRegisterMessage, RegisterReply> actionTrigger;

    @Override
    public void register(RegisterMessage message, StreamObserver<RegisterReply> responseObserver) {
        var validatedMsg = MessageMapper.INSTANCE.convertToValidatedRegisterMessage(message);
        actionTrigger
                .init(action) // Init action for later trigger
                .proceed(validatedMsg, responseObserver);
    }
}
