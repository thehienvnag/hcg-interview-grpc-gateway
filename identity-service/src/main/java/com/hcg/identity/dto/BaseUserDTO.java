package com.hcg.identity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseUserDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
}
