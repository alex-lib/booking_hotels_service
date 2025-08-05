package com.service.bookinghotels.web.controllers;
import com.service.bookinghotels.mappers.room.RoomMapper;
import com.service.bookinghotels.services.RoomService;
import com.service.bookinghotels.web.dto.hotel.HotelFilter;
import com.service.bookinghotels.web.dto.hotel.HotelsListResponse;
import com.service.bookinghotels.web.dto.room.RoomFilter;
import com.service.bookinghotels.web.dto.room.RoomRequest;
import com.service.bookinghotels.web.dto.room.RoomResponse;
import com.service.bookinghotels.web.dto.room.RoomsListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    private final RoomMapper roomMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoomResponse createRoom(@RequestBody RoomRequest roomRequest) {
        return roomMapper.roomToRoomResponse((roomService
                .createRoom(roomMapper.roomRequestToRoom(roomRequest))));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public RoomResponse findRoomById(@PathVariable Long id) {
        return roomMapper.roomToRoomResponse(roomService
                .getRoomById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoomResponse updateRoom(@PathVariable Long id, @RequestBody RoomRequest roomRequest) {
        return roomMapper.roomToRoomResponse(roomService
                .updateRoom(id, roomMapper.roomRequestToRoom(id, roomRequest)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find-all")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public RoomsListResponse findAllRooms(@Valid RoomFilter filter) {
        return roomMapper.roomlListToRoomsListResponse(roomService.getAllRooms(filter));
    }
}