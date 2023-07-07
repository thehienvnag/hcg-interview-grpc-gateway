package com.hcg.booking.service;

import com.hcg.booking.dto.RoomDTO;

import java.time.LocalDateTime;

public interface RoomService {
    RoomDTO findById(int id);
}
