package com.hcg.gateway.connects.delete_booking.dto;

import com.hcg.gateway.connects.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookingResponse implements BaseResponse {
    private Boolean success;
}
