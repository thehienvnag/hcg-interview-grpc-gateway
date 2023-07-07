package com.hcg.booking.mapper;


import com.hcg.booking.dto.BookingDTO;
import com.hcg.booking.grpc.service.CreateBookingMessage;
import com.hcg.booking.grpc.service.DeleteBookingMessage;
import com.hcg.booking.message.ValidatedCreateBookingMessage;
import com.hcg.booking.message.ValidatedDeleteBookingMessage;
import com.hcg.booking.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);
    Booking convertToBooking(BookingDTO bookingDTO);
}
