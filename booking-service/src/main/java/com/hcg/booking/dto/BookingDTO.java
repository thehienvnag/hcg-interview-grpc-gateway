package com.hcg.booking.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookingDTO {
    private Integer roomId;
    private String roomName;
    private Integer guestId;
    private String guestLastName;
    private String guestFirstName;
    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime;
}
