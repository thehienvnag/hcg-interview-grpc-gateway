package com.hcg.booking.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InputErrorDTO {
    private String field;
    private String message;
}
