package com.hcg.booking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
@Getter
@Setter
@Entity
@Table(name = "hotels")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

}
