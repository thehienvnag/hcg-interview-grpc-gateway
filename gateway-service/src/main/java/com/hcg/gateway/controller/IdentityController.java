package com.hcg.gateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.gateway.qualifiers.IdentityChannelQualifier;
import com.hcg.gateway.connects.BaseResponse;
import com.hcg.gateway.connects.login.LoginService;
import com.hcg.gateway.connects.login.dto.LoginRequest;
import com.hcg.gateway.connects.register.RegisterService;
import com.hcg.gateway.connects.register.dto.RegisterRequest;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@RestController
@RequestMapping("/api")
public class IdentityController {

    @Autowired
    @IdentityChannelQualifier
    private ManagedChannel channel;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;


	@PostMapping("/v1/login")
	public ResponseEntity<BaseResponse> loginToSystem(@RequestBody @Valid LoginRequest request) throws InvalidProtocolBufferException, JsonProcessingException {

        var loginResponse = loginService.proceed(channel, request);
        return ResponseEntity.ok(loginResponse);
	}

    @PostMapping("/v1/register")
    public ResponseEntity<BaseResponse> registerToSystem(@RequestBody @Valid RegisterRequest request) throws InvalidProtocolBufferException, JsonProcessingException {

        var registerRes = registerService.proceed(channel, request);
        return ResponseEntity.ok(registerRes);
    }

}
