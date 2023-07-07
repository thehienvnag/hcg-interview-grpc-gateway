package com.hcg.identity.service;


import com.hcg.identity.dto.AuthenticatedUserDto;
import com.hcg.identity.dto.BaseUserDTO;
import com.hcg.identity.message.ValidatedRegisterMessage;
import com.hcg.identity.model.User;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
public interface UserService {

	BaseUserDTO findUserById(Integer id);

	AuthenticatedUserDto findUserByEmail(String email);

	User createUser(ValidatedRegisterMessage registerMessage);

	boolean existsByEmail(String email);
}
