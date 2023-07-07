package com.hcg.booking.repository;

import com.hcg.booking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
