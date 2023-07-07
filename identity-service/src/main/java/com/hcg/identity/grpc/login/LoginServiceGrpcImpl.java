package com.hcg.identity.grpc.login;

import com.hcg.identity.grpc.ActionTrigger;
import com.hcg.identity.grpc.service.LoginMessage;
import com.hcg.identity.grpc.service.LoginReply;
import com.hcg.identity.grpc.service.LoginServiceGrpc;
import com.hcg.identity.mapper.MessageMapper;
import com.hcg.identity.message.ValidatedLoginMessage;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class LoginServiceGrpcImpl extends LoginServiceGrpc.LoginServiceImplBase {

    private final LoginAction action;

    private final ActionTrigger<ValidatedLoginMessage, LoginReply> actionTrigger;

    @Override
    public void login(LoginMessage message, StreamObserver<LoginReply> responseObserver) {
        var validatedMsg = MessageMapper.INSTANCE.convertToValidatedLoginMessage(message);
        actionTrigger
                .init(action) // Init action for later trigger
                .proceed(validatedMsg, responseObserver);
    }
}
