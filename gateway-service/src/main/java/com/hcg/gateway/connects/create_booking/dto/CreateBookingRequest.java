package com.hcg.gateway.connects.create_booking.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateBookingRequest {
    @NotNull
    private Integer roomId;
    @NotNull
    private Integer guestId;
    @NotNull
    private String checkinTime;
    @NotNull
    private String checkoutTime;
}
