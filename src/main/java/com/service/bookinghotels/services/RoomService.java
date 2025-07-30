package com.service.bookinghotels.services;
import com.service.bookinghotels.entities.Room;

public interface RoomService {

    Room getRoomById(Long id);

    Room createRoom(Room room);

    Room updateRoom(Long id, Room room);

    void deleteRoom(Long id);

    Room getRoomByName(String name);
}