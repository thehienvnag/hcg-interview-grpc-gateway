package com.hcg.identity.grpc.get_user_details.impl;

import com.hcg.identity.dto.ActionResultDTO;
import com.hcg.identity.grpc.get_user_details.GetUserDetailsAction;
import com.hcg.identity.grpc.service.GetUserDetailsReply;
import com.hcg.identity.grpc.service.RegisterReply;
import com.hcg.identity.interceptors.UserTokenValidationInterceptor;
import com.hcg.identity.message.ValidatedGetUserDetailsMessage;
import com.hcg.identity.message.ValidatedRegisterMessage;
import com.hcg.identity.service.UserService;
import io.grpc.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserDetailsActionImpl implements GetUserDetailsAction {

    private final UserService userService;

    @Override
    public ActionResultDTO<GetUserDetailsReply> proceedInternal(ValidatedGetUserDetailsMessage message) {
        int payloadUserId = message.getUserId();
        int authUserId = UserTokenValidationInterceptor.USER_IDENTITY.get();

        if (payloadUserId != authUserId) {
            Status status = Status.PERMISSION_DENIED
                    .withDescription("User id in payload and token does not match");
            return new ActionResultDTO<>(status, null);
        }

        var user = userService.findUserById(authUserId);
        if (user == null) {
            Status status = Status.NOT_FOUND
                    .withDescription("User not found");
            return new ActionResultDTO<>(status, null);
        }

        GetUserDetailsReply reply = GetUserDetailsReply.newBuilder()
                .setSuccess(true)
                .setUserId(user.getId())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .build();

        return new ActionResultDTO<>(Status.OK, reply);
    }
}
