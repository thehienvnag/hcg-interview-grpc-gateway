package com.hcg.gateway.connects.login.dto;

import com.hcg.gateway.connects.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse implements BaseResponse {
    private boolean success;
    private String accessToken;
}
