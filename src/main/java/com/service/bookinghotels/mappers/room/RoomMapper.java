package com.service.bookinghotels.mappers.room;
import com.service.bookinghotels.entities.Room;
import com.service.bookinghotels.entities.UnavailableDate;
import com.service.bookinghotels.web.dto.room.RoomRequest;
import com.service.bookinghotels.web.dto.room.RoomResponse;
import com.service.bookinghotels.web.dto.room.RoomsListResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@DecoratedWith(RoomMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    Room roomRequestToRoom(RoomRequest roomRequest);

    @Mapping(source = "hotelId", target = "id")
    Room roomRequestToRoom(Long hotelId, RoomRequest roomRequest);

    RoomResponse roomToRoomResponse(Room room);

   default RoomsListResponse roomlListToRoomsListResponse(List<Room> rooms) {
       List<RoomResponse> list = rooms.stream()
               .map(this::roomToRoomResponse)
               .toList();
       return RoomsListResponse.builder()
               .rooms(list)
               .build();
   }

    default Set<LocalDate> mapUnavailableDatesToDates(Set<UnavailableDate> unavailableDates) {
        if (unavailableDates == null) {
            return Collections.emptySet();
        }
        return unavailableDates.stream()
                .map(UnavailableDate::getDate)
                .collect(Collectors.toSet());
    }
}