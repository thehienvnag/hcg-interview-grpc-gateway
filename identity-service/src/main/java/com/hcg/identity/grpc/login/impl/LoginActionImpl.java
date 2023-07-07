package com.hcg.identity.grpc.login.impl;

import com.hcg.identity.dto.ActionResultDTO;
import com.hcg.identity.grpc.login.LoginAction;
import com.hcg.identity.grpc.service.LoginReply;
import com.hcg.identity.message.ValidatedLoginMessage;
import com.hcg.identity.service.JwtService;
import com.hcg.identity.service.UserService;
import io.grpc.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginActionImpl implements LoginAction {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtService jwtService;

    @Override
    public ActionResultDTO<LoginReply> proceedInternal(ValidatedLoginMessage message) {
        Status status;
        LoginReply reply = null;
        try {
            var token = new UsernamePasswordAuthenticationToken(message.getEmail(), message.getPassword());
            authenticationManager.authenticate(token);

            status = Status.OK;

            var user = userService.findUserByEmail(message.getEmail());
            String accessToken = jwtService.generateToken(user);

            reply = LoginReply.newBuilder()
                    .setSuccess(Boolean.TRUE)
                    .setAccessToken(accessToken)
                    .build();
        } catch (AuthenticationException ex) {
            status = Status.UNAUTHENTICATED
                    .withDescription(ex.getMessage())
                    .withCause(ex.getCause());
        }

        return new ActionResultDTO<>(status, reply);
    }
}
