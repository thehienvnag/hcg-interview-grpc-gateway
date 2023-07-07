package com.hcg.booking.interceptors;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hcg.booking.service.JwtService;
import com.hcg.booking.utils.SecurityConst;
import io.grpc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserTokenValidationInterceptor implements ServerInterceptor {

    public static final Context.Key<Integer> USER_IDENTITY = Context.key("userId");
    public static final Context.Key<String> ACCESS_TOKEN = Context.key("Authorization");

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
        Context context = Context.current()
                .withValue(USER_IDENTITY, userId)
                .withValue(ACCESS_TOKEN, userToken);
        return Contexts.interceptCall(context, call, headers, next);
    }

}
