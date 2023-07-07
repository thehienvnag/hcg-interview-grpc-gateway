package com.hcg.booking.grpc.create_booking;

import com.hcg.booking.grpc.Action;
import com.hcg.booking.grpc.service.CreateBookingReply;
import com.hcg.booking.message.ValidatedCreateBookingMessage;

public interface CreateBookingAction extends Action<ValidatedCreateBookingMessage, CreateBookingReply> {
}
