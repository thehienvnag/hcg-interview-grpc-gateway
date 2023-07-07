package com.hcg.booking.grpc.delete_booking;

import com.hcg.booking.grpc.Action;
import com.hcg.booking.grpc.service.DeleteBookingReply;
import com.hcg.booking.message.ValidatedDeleteBookingMessage;

public interface DeleteBookingAction extends Action<ValidatedDeleteBookingMessage, DeleteBookingReply> {
}
