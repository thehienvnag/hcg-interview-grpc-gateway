package com.hcg.booking.repository;

import com.hcg.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT count(1)   " +
            "        FROM Bookings   " +
            "        WHERE room_id = :roomId  " +
            "        AND checkin_time <= :checkinTime  " +
            "        AND checkout_time >= :checkoutTime  " +
            "        AND active = true", nativeQuery = true)
    int countBookingAvailable(@Param("roomId") long roomId, @Param("checkoutTime") LocalDate checkoutTime, @Param("checkinTime") LocalDate checkinTime);

    boolean existsByGuestId(Integer guestId);
}
