package com.hcg.gateway.connects.register.dto;

import com.hcg.gateway.connects.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse implements BaseResponse {
    private boolean success;
    private String email;
    private int id;
}
