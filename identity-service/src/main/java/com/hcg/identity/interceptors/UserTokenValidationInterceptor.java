package com.hcg.identity.interceptors;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hcg.identity.service.JwtService;
import com.hcg.identity.utils.SecurityConst;
import io.grpc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserTokenValidationInterceptor implements ServerInterceptor {

    public static final Context.Key<Integer> USER_IDENTITY = Context.key("userId");

    private final JwtService jwtService;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        String userToken = headers.get(SecurityConst.AUTHORIZATION_KEY);

        // Validate the user token here
        DecodedJWT decodedJWT = jwtService.validateToken(userToken);

        if (decodedJWT == null) {
            // Return an error status if the token is invalid
            call.close(Status.UNAUTHENTICATED.withDescription("Invalid access token!"), new Metadata());
            return new ServerCall.Listener<>() {};
        }

        Integer userId = Integer.parseInt(decodedJWT.getSubject());
        Context context = Context.current().withValue(USER_IDENTITY, userId);
        return Contexts.interceptCall(context, call, headers, next);
    }

}
