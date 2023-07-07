package com.hcg.identity.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "create_at")
	private LocalDateTime createAt;

}
