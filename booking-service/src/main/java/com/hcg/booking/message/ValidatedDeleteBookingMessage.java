package com.hcg.booking.message;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ValidatedDeleteBookingMessage {
    @NotNull
    private Integer bookingId;
}
