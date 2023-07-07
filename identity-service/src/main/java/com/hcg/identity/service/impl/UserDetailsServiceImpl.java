package com.hcg.identity.service.impl;

import com.hcg.identity.dto.AuthenticatedUserDto;
import com.hcg.identity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final String USERNAME_OR_PASSWORD_INVALID = "Invalid username or password.";

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) {

		final AuthenticatedUserDto authenticatedUser = userService.findUserByEmail(email);

		if (Objects.isNull(authenticatedUser)) {
			throw new UsernameNotFoundException(USERNAME_OR_PASSWORD_INVALID);
		}

		final String authenticatedUsername = authenticatedUser.getEmail();
		final String authenticatedPassword = authenticatedUser.getPassword();

		return new User(authenticatedUsername, authenticatedPassword, Collections.emptyList());
	}
}
