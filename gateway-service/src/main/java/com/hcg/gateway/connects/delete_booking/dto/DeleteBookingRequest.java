package com.hcg.gateway.connects.delete_booking.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteBookingRequest {
    @NotNull
    private Integer bookingId;
}
