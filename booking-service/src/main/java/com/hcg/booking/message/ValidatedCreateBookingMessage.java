package com.hcg.booking.message;

import com.hcg.booking.validators.ValidFutureOrPresent;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ValidatedCreateBookingMessage {
    @Digits(integer = 10, fraction = 0)
    @Positive
    private Integer roomId;
    @Digits(integer = 10, fraction = 0)
    @Positive
    private Integer guestId;
    @ValidFutureOrPresent
    private String checkinTime;
    @ValidFutureOrPresent
    private String checkoutTime;
}
