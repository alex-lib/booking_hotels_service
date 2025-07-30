package com.service.bookinghotels.web.controllers;
import com.service.bookinghotels.mappers.room.RoomMapper;
import com.service.bookinghotels.services.RoomService;
import com.service.bookinghotels.web.dto.room.RoomRequest;
import com.service.bookinghotels.web.dto.room.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    private final RoomMapper roomMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RoomResponse createRoom(@RequestBody RoomRequest roomRequest) {
        return roomMapper.roomToRoomResponse((roomService
                .createRoom(roomMapper.roomRequestToRoom(roomRequest))));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public RoomResponse findRoomById(@PathVariable Long id) {
        return roomMapper.roomToRoomResponse(roomService
                .getRoomById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public RoomResponse updateRoom(@PathVariable Long id, @RequestBody RoomRequest roomRequest) {
        return roomMapper.roomToRoomResponse(roomService
                .updateRoom(id, roomMapper.roomRequestToRoom(id, roomRequest)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}