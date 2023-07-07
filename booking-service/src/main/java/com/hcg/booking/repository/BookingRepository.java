package com.hcg.booking.repository;

import com.hcg.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT count(1)   " +
            "        FROM Bookings   " +
            "        WHERE room_id = :roomId  " +
            "        AND checkin_time <= :checkoutTime  " +
            "        AND checkout_time >= :checkinTime  " +
            "        AND active = true", nativeQuery = true)
    int countAlreadyBooked(@Param("roomId") long roomId, @Param("checkoutTime") LocalDate checkoutTime, @Param("checkinTime") LocalDate checkinTime);

    @Query(value = "SELECT count(1)   " +
            "        FROM Bookings   " +
            "        WHERE id = :bookingId  " +
            "        AND guest_id = :guestId " +
            "        AND active = true  ", nativeQuery = true)
    int existByIdAndGuestId(@Param("bookingId") Integer bookingId, @Param("guestId") Integer guestId);
}
