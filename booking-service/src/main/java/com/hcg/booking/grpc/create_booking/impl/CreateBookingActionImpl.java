package com.hcg.booking.grpc.create_booking.impl;

import com.hcg.booking.connects.get_user_details.GetUserDetailsService;
import com.hcg.booking.dto.ActionResultDTO;
import com.hcg.booking.dto.BookingDTO;
import com.hcg.booking.dto.RoomDTO;
import com.hcg.booking.grpc.create_booking.CreateBookingAction;
import com.hcg.booking.grpc.service.CreateBookingReply;
import com.hcg.booking.grpc.service.CreateBookingReplyOrBuilder;
import com.hcg.booking.interceptors.UserTokenValidationInterceptor;
import com.hcg.booking.message.ValidatedCreateBookingMessage;
import com.hcg.booking.qualifiers.IdentityChannelQualifier;
import com.hcg.booking.service.BookingService;
import com.hcg.booking.service.RoomService;
import com.hcg.identity.grpc.service.GetUserDetailsReply;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class CreateBookingActionImpl implements CreateBookingAction {

    @Autowired
    @IdentityChannelQualifier
    private ManagedChannel identityChannel;

    @Autowired
    private GetUserDetailsService getUserDetailsService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Override
    public ActionResultDTO<CreateBookingReply> proceedInternal(ValidatedCreateBookingMessage message) {

        // Check user owned account
        int userId = UserTokenValidationInterceptor.USER_IDENTITY.get();
        if (userId != message.getGuestId()) {
            return new ActionResultDTO<>(Status.PERMISSION_DENIED
                    .withDescription("User id authenticated and guest id does not match"), null);
        }

        // Check user exists
        String token = UserTokenValidationInterceptor.ACCESS_TOKEN.get();
        GetUserDetailsReply userDetailsReply = getUserDetailsService.proceed(token, identityChannel, message.getGuestId());
        if (!userDetailsReply.getSuccess()) {
            return new ActionResultDTO<>(Status.PERMISSION_DENIED
                    .withDescription("User not found in system"), null);
        }

        // Check reservation date
        LocalDate checkinTime = LocalDate.parse(message.getCheckinTime(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate checkoutTime = LocalDate.parse(message.getCheckoutTime(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if (checkinTime.isAfter(checkoutTime)) {
            return new ActionResultDTO<>(Status.INVALID_ARGUMENT
                    .withDescription("Checkin time cannot be after checkout time"), null);
        }

        if (ChronoUnit.DAYS.between(checkinTime, checkoutTime) < 1) {
            return new ActionResultDTO<>(Status.INVALID_ARGUMENT
                    .withDescription("Booking duration cannot be less than 1 day"), null);
        }

        // Check room exists
        RoomDTO roomDTO = roomService.findById(message.getRoomId());
        if (roomDTO == null) {
            return new ActionResultDTO<>(Status.INVALID_ARGUMENT
                    .withDescription("Room not found in system"), null);
        }

        // Check room is available
        if (!bookingService.isAvailable(message.getRoomId(), checkoutTime, checkinTime)) {
            return new ActionResultDTO<>(Status.INVALID_ARGUMENT
                    .withDescription("Room is not available for your checkin and checkout date"), null);
        }


        //Create booking
        BookingDTO bookingDTO = BookingDTO.builder()
                .roomId(message.getRoomId())
                .roomName(roomDTO.getRoomName())
                .guestId(message.getGuestId())
                .guestFirstName(userDetailsReply.getFirstName())
                .guestLastName(userDetailsReply.getLastName())
                .checkinTime(checkinTime.atStartOfDay())
                .checkoutTime(checkoutTime.atStartOfDay())
                .build();
        int createdBookingId = bookingService.createBooking(bookingDTO);

        CreateBookingReply reply = CreateBookingReply.newBuilder()
                .setSuccess(true)
                .setBookingId(createdBookingId)
                .setGuestFirstName(userDetailsReply.getFirstName())
                .setGuestLastName(userDetailsReply.getLastName())
                .setRoomName(roomDTO.getRoomName())
                .build();


        return new ActionResultDTO<>(Status.OK, reply);
    }
}
