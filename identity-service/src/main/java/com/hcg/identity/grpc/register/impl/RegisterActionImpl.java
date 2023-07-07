package com.hcg.identity.grpc.register.impl;

import com.hcg.identity.dto.ActionResultDTO;
import com.hcg.identity.grpc.register.RegisterAction;
import com.hcg.identity.grpc.service.RegisterReply;
import com.hcg.identity.message.ValidatedRegisterMessage;
import com.hcg.identity.service.UserService;
import io.grpc.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterActionImpl implements RegisterAction {

    private final UserService userService;

    @Override
    public ActionResultDTO<RegisterReply> proceedInternal(ValidatedRegisterMessage message) {
        if (userService.existsByEmail(message.getEmail())) {
            return new ActionResultDTO<>(Status.ALREADY_EXISTS.withDescription("Email already exists!"), null);
        }

        var created = userService.createUser(message);

        RegisterReply reply = RegisterReply.newBuilder()
                .setSuccess(true)
                .setId(created.getId())
                .setEmail(created.getEmail())
                .build();

        return new ActionResultDTO<>(Status.OK, reply);
    }
}
