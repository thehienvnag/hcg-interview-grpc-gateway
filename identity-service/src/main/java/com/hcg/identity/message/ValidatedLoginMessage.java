package com.hcg.identity.message;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ValidatedLoginMessage {
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
