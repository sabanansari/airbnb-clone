package com.ansari.projects.airBnbApp.service;

import com.ansari.projects.airBnbApp.dto.RoomDto;
import com.ansari.projects.airBnbApp.entity.Room;

import java.util.List;

public interface RoomService {

    RoomDto createNewRoom(Long hotelId,RoomDto roomDto);

    List<RoomDto> getAllRoomsInHotel(Long hotelId);

    RoomDto getRoomById(Long roomId);

    void deleteRoomById(Long roomId);
}
