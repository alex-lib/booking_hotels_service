package com.service.bookinghotels.services.impl;
import com.service.bookinghotels.entities.Room;
import com.service.bookinghotels.exceptions.EntityIsExistedException;
import com.service.bookinghotels.exceptions.EntityNotFoundException;
import com.service.bookinghotels.repositories.RoomRepository;
import com.service.bookinghotels.services.RoomService;
import com.service.bookinghotels.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    @Override
    public Room getRoomById(Long id) {
        log.info("Call method getRoomById to find room with id: {}", id);
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room is not found"));
    }

    @Transactional
    @Override
    public Room createRoom(Room room) {
        log.info("Call method createRoom to create room: {}", room);
        if (getRoomByName(room.getName()) != null) {
            throw new EntityIsExistedException("Such room's name is already existed");
        }
        return roomRepository.save(room);
    }

    @Transactional
    @Override
    public Room updateRoom(Long id, Room room) {
        log.info("Call method updateRoom to update room: {}", room);
        Room existedRoom = getRoomById(id);
        BeanUtils.copyNonNullProperties(room, existedRoom);
        return roomRepository.save(existedRoom);
    }

    @Transactional
    @Override
    public void deleteRoom(Long id) {
        log.info("Call method deleteRoom to delete room with id: {}", id);
        roomRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Room getRoomByName(String name) {
        log.info("Call method getRoomByName to find room with name: {}", name);
        return roomRepository.getRoomByName(name);
    }
}