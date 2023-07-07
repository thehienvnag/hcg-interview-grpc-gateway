package com.hcg.identity.message;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ValidatedRegisterMessage {
    @Email
    @NotEmpty
    @Length(max = 255)
    private String email;

    @Pattern(regexp = "[a-zA-Z0-9]{8,}")
    @Length(max = 255)
    private String password;

    @NotEmpty
    @Length(max = 255)
    private String firstName;

    @NotEmpty
    @Length(max = 255)
    private String lastName;
}
