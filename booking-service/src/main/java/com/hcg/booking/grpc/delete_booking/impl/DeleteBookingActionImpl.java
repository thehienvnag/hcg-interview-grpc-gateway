package com.hcg.booking.grpc.delete_booking.impl;

import com.hcg.booking.dto.ActionResultDTO;
import com.hcg.booking.grpc.delete_booking.DeleteBookingAction;
import com.hcg.booking.grpc.service.DeleteBookingReply;
import com.hcg.booking.interceptors.UserTokenValidationInterceptor;
import com.hcg.booking.message.ValidatedDeleteBookingMessage;
import com.hcg.booking.service.BookingService;
import com.hcg.identity.grpc.service.GetUserDetailsReply;
import io.grpc.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBookingActionImpl implements DeleteBookingAction {

    private final BookingService bookingService;


    @Override
    public ActionResultDTO<DeleteBookingReply> proceedInternal(ValidatedDeleteBookingMessage message) {

        Integer bookingId = message.getBookingId();

        // Check if user owns booking
        int userId = UserTokenValidationInterceptor.USER_IDENTITY.get();
        if (!bookingService.existsByIdAndGuestId(bookingId, userId)) {
            return new ActionResultDTO<>(Status.NOT_FOUND
                    .withDescription("Booking not found"), null);
        }

        bookingService.cancelBooking(bookingId);

        return new ActionResultDTO<>(Status.OK, DeleteBookingReply.newBuilder()
                .setSuccess(true)
                .build());
    }
}
