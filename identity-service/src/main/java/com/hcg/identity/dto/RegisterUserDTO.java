package com.hcg.identity.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
