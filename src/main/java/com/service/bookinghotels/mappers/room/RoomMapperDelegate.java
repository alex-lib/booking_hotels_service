package com.service.bookinghotels.mappers.room;
import com.service.bookinghotels.entities.Room;
import com.service.bookinghotels.services.HotelService;
import com.service.bookinghotels.web.dto.room.RoomRequest;
import com.service.bookinghotels.web.dto.room.RoomResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class RoomMapperDelegate implements RoomMapper {

    private final HotelService hotelService;

    @Override
    public Room roomRequestToRoom(RoomRequest roomRequest) {
        return Room.builder()
                .name(roomRequest.getName())
                .description(roomRequest.getDescription())
                .number(roomRequest.getNumber())
                .price(roomRequest.getPrice())
                .maxPeoplePerRoom(roomRequest.getMaxPeoplePerRoom())
                .hotel(hotelService.getHotelById(roomRequest.getHotelId()))
                .build();
    }

    @Override
    public Room roomRequestToRoom(Long hotelId, RoomRequest roomRequest) {
        Room room = roomRequestToRoom(roomRequest);
        room.setId(hotelId);
        return room;
    }

    @Override
    public RoomResponse roomToRoomResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .description(room.getDescription())
                .number(room.getNumber())
                .price(room.getPrice())
                .maxPeoplePerRoom(room.getMaxPeoplePerRoom())
                .busyDates(room.getBusyDates())
                .hotelId(room.getHotel().getId())
                .build();
    }
}