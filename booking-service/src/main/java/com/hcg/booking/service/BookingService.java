package com.hcg.booking.service;

import com.hcg.booking.dto.BookingDTO;
import com.hcg.booking.message.ValidatedCreateBookingMessage;

import java.time.LocalDate;

public interface BookingService {
    boolean isAvailable(Integer id, LocalDate checkoutTime, LocalDate checkinTime);
    int createBooking(BookingDTO message);
    void cancelBooking(Integer id);
    boolean existsById(Integer id);
    boolean existsByGuestId(Integer guestId);
}
