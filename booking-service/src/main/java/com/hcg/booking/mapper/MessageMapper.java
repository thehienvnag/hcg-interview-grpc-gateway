package com.hcg.booking.mapper;


import com.hcg.booking.grpc.service.CreateBookingMessage;
import com.hcg.booking.grpc.service.DeleteBookingMessage;
import com.hcg.booking.message.ValidatedCreateBookingMessage;
import com.hcg.booking.message.ValidatedDeleteBookingMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);
    ValidatedCreateBookingMessage convertToValidatedCreateBookingMessage(CreateBookingMessage registerMessage);
    ValidatedDeleteBookingMessage convertToValidatedDeleteBookingMessage(DeleteBookingMessage registerMessage);
}
