package com.service.bookinghotels.mappers.booking;
import com.service.bookinghotels.entities.Booking;
import com.service.bookinghotels.mappers.room.RoomMapper;
import com.service.bookinghotels.mappers.user.UserMapper;
import com.service.bookinghotels.web.dto.booking.BookingRequest;
import com.service.bookinghotels.web.dto.booking.BookingResponse;
import com.service.bookinghotels.web.dto.booking.BookingsListResponse;
import com.service.bookinghotels.web.dto.kafkadto.BookingRoomEvent;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

@DecoratedWith(BookingMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, RoomMapper.class})
public interface BookingMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "room", ignore = true)
    Booking bookingRequestToBooking(BookingRequest bookingRequest, UserDetails user);

    @Mapping(source = "bookingId", target = "id")
    Booking bookingRequestToBooking(Long bookingId, BookingRequest bookingRequest, UserDetails user);

    BookingResponse bookingToBookingResponse(Booking booking);

    default BookingsListResponse bookingListToBookingsListResponse(List<Booking> bookings) {
        List<BookingResponse> list = bookings.stream()
                .map(this::bookingToBookingResponse)
                .toList();
        return BookingsListResponse.builder()
                .bookings(list)
                .build();
    }

    BookingRoomEvent bookingToBookingRoomEvent(Booking booking, UserDetails user);
}