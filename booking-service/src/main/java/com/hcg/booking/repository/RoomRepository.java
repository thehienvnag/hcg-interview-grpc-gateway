package com.hcg.booking.repository;

import com.hcg.booking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

/**
 * Created on July 2023
 *
 * @author Ivan
 */
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
