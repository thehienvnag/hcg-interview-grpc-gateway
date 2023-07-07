package com.hcg.booking.service.impl;

import com.hcg.booking.dto.BookingDTO;
import com.hcg.booking.mapper.BookingMapper;
import com.hcg.booking.model.Booking;
import com.hcg.booking.repository.BookingRepository;
import com.hcg.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public boolean isAvailable(Integer id, LocalDate checkoutTime, LocalDate checkinTime) {
        return bookingRepository.countAlreadyBooked(id, checkoutTime, checkinTime) == 0;
    }

    @Override
    public int createBooking(BookingDTO message) {
        Booking bookingToSave = BookingMapper.INSTANCE.convertToBooking(message);
        bookingToSave.setActive(true);
        bookingToSave.setCreatedAt(LocalDateTime.now());
        bookingRepository.save(bookingToSave);
        return bookingToSave.getId();
    }

    @Override
    public void cancelBooking(Integer id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setActive(false);
        booking.setCreatedAt(LocalDateTime.now());
        bookingRepository.save(booking);
    }

    @Override
    public boolean existsById(Integer id) {
        return bookingRepository.existsById(id);
    }

    @Override
    public boolean existsByIdAndGuestId(Integer bookingId, Integer guestId) {
        return bookingRepository.existByIdAndGuestId(bookingId, guestId) > 0;
    }
}
