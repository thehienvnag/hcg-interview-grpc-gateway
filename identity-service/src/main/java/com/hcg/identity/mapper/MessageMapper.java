package com.hcg.identity.mapper;


import com.hcg.identity.grpc.service.GetUserDetailsMessage;
import com.hcg.identity.grpc.service.LoginMessage;
import com.hcg.identity.grpc.service.RegisterMessage;
import com.hcg.identity.message.ValidatedGetUserDetailsMessage;
import com.hcg.identity.message.ValidatedLoginMessage;
import com.hcg.identity.message.ValidatedRegisterMessage;
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
    ValidatedRegisterMessage convertToValidatedRegisterMessage(RegisterMessage message);
    ValidatedLoginMessage convertToValidatedLoginMessage(LoginMessage message);
    ValidatedGetUserDetailsMessage convertToValidatedGetUserDetailsMessage(GetUserDetailsMessage message);
}
