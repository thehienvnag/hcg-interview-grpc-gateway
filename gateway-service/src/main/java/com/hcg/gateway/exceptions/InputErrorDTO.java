package com.hcg.gateway.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputErrorDTO {
    private String field;
    private String message;
}
