package com.hcg.booking.dto;

import io.grpc.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class ActionResultDTO<T> {
    @NotNull
    private Status status;

    private T result;
}
