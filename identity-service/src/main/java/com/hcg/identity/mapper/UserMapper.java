package com.hcg.identity.mapper;

import com.hcg.identity.dto.AuthenticatedUserDto;
import com.hcg.identity.dto.BaseUserDTO;
import com.hcg.identity.message.ValidatedRegisterMessage;
import com.hcg.identity.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	AuthenticatedUserDto convertToAuthenticatedUserDto(User user);
	User convertToUser(ValidatedRegisterMessage user);
	BaseUserDTO convertToBaseUserDTO(User user);

}
