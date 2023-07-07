package com.hcg.gateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hcg.gateway.qualifiers.BookingChannelQualifier;
import com.hcg.gateway.connects.BaseResponse;
import com.hcg.gateway.connects.create_booking.CreateBookingService;
import com.hcg.gateway.connects.create_booking.dto.CreateBookingRequest;
import com.hcg.gateway.connects.delete_booking.DeleteBookingService;
import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    @BookingChannelQualifier
    private ManagedChannel channel;

    @Autowired
    private CreateBookingService createBookingService;

    @Autowired
    private DeleteBookingService deleteBookingService;


	@PostMapping("/v1/booking")
	public ResponseEntity<BaseResponse> createBooking(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody @Valid CreateBookingRequest request) throws InvalidProtocolBufferException, JsonProcessingException {

        var loginResponse = createBookingService.proceed(token, channel, request);
        return ResponseEntity.ok(loginResponse);
	}

    @DeleteMapping("/v1/booking/{id}")
    public ResponseEntity<BaseResponse> deleteBooking(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") @Valid @Positive Integer bookingId) throws InvalidProtocolBufferException, JsonProcessingException {

        var loginResponse = deleteBookingService.proceed(token, channel, bookingId.toString());
        return ResponseEntity.ok(loginResponse);
    }

}
