package com.service.bookinghotels.services;
import com.service.bookinghotels.entities.Room;
import com.service.bookinghotels.web.dto.room.RoomFilter;
import java.util.List;

public interface RoomService {

    Room getRoomById(Long id);

    Room createRoom(Room room);

    Room updateRoom(Long id, Room room);

    void deleteRoom(Long id);

    Room getRoomByName(String name);

    List<Room> getAllRooms(RoomFilter filter);
}