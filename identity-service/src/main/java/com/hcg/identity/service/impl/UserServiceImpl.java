package com.hcg.identity.service.impl;

import com.hcg.identity.dto.AuthenticatedUserDto;
import com.hcg.identity.dto.BaseUserDTO;
import com.hcg.identity.mapper.UserMapper;
import com.hcg.identity.message.ValidatedRegisterMessage;
import com.hcg.identity.model.User;
import com.hcg.identity.repository.UserRepository;
import com.hcg.identity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public BaseUserDTO findUserById(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		return UserMapper.INSTANCE.convertToBaseUserDTO(user);
	}

	@Override
	public AuthenticatedUserDto findUserByEmail(String email) {
		var user = userRepository.findByEmail(email);
		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}

	@Override
	public User createUser(ValidatedRegisterMessage message) {
		User user = UserMapper.INSTANCE.convertToUser(message);
		user.setCreateAt(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
}
