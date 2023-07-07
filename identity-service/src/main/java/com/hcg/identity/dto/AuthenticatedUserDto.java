package com.hcg.identity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUserDto {
	private Long id;

	private String email;

	private String password;

}
