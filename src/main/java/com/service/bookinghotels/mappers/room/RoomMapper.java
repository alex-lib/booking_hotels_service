package com.service.bookinghotels.mappers.room;
import com.service.bookinghotels.entities.Room;
import com.service.bookinghotels.web.dto.room.RoomRequest;
import com.service.bookinghotels.web.dto.room.RoomResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(RoomMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    Room roomRequestToRoom(RoomRequest roomRequest);

    @Mapping(source = "hotelId", target = "id")
    Room roomRequestToRoom(Long hotelId, RoomRequest roomRequest);

    RoomResponse roomToRoomResponse(Room room);
}