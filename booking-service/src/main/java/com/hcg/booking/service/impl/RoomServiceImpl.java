package com.hcg.booking.service.impl;

import com.hcg.booking.dto.RoomDTO;
import com.hcg.booking.model.Room;
import com.hcg.booking.repository.RoomRepository;
import com.hcg.booking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    @Override
    public RoomDTO findById(int id) {
        Room room = roomRepository.findById(id).orElse(null);

        if (room != null) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoomName(room.getName());
            return roomDTO;
        }
        return null;
    }
}
