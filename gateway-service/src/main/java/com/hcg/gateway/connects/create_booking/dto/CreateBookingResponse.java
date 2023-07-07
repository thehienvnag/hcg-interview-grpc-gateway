package com.hcg.gateway.connects.create_booking.dto;

import com.hcg.gateway.connects.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingResponse implements BaseResponse {
    private Boolean success;
    private Integer bookingId;
    private String roomName;
    private String guestLastName;
    private String guestFirstName;
}
