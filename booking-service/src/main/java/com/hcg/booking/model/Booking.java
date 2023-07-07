package com.hcg.booking.model;

import lombok.Getter;
import lombok.Setter;

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
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "room_id")
	private Integer roomId;

	@Column(name = "room_name")
	private String roomName;

	@Column(name = "guest_id")
	private Integer guestId;

	@Column(name = "guest_lastname")
	private String guestLastName;

	@Column(name = "guest_firstname")
	private String guestFirstName;

	@Column(name = "checkin_time")
	private LocalDateTime checkinTime;

	@Column(name = "checkout_time")
	private LocalDateTime checkoutTime;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "active")
	private Boolean active;

}
