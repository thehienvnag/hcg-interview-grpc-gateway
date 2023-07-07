package com.hcg.identity.grpc.get_user_details;

import com.hcg.identity.grpc.ActionTrigger;
import com.hcg.identity.grpc.service.GetUserDetailsMessage;
import com.hcg.identity.grpc.service.GetUserDetailsReply;
import com.hcg.identity.grpc.service.GetUserDetailsServiceGrpc;
import com.hcg.identity.interceptors.UserTokenValidationInterceptor;
import com.hcg.identity.mapper.MessageMapper;
import com.hcg.identity.message.ValidatedGetUserDetailsMessage;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService(interceptors = {UserTokenValidationInterceptor.class})
@RequiredArgsConstructor
public class GetUserDetailsServiceGrpcImpl extends GetUserDetailsServiceGrpc.GetUserDetailsServiceImplBase {

    private final GetUserDetailsAction action;

    private final ActionTrigger<ValidatedGetUserDetailsMessage, GetUserDetailsReply> actionTrigger;

    @Override
    public void getDetails(GetUserDetailsMessage request, StreamObserver<GetUserDetailsReply> responseObserver) {
        var validatedMsg = MessageMapper.INSTANCE.convertToValidatedGetUserDetailsMessage(request);
        actionTrigger
                .init(action) // Init action for later trigger
                .proceed(validatedMsg, responseObserver);
    }


}
