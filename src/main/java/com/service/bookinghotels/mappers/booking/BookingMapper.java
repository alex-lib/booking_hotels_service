package com.service.bookinghotels.mappers.booking;
import com.service.bookinghotels.entities.Booking;
import com.service.bookinghotels.web.dto.booking.BookingRequest;
import com.service.bookinghotels.web.dto.booking.BookingResponse;
import com.service.bookinghotels.web.dto.booking.BookingsListResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(BookingMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    Booking bookingRequestToBooking(BookingRequest bookingRequest);

    @Mapping(source = "bookingId", target = "id")
    Booking bookingRequestToBooking(Long bookingId, BookingRequest bookingRequest);

    BookingResponse bookingToBookingResponse(Booking booking);

    BookingsListResponse bookingListToBookingsListResponse(List<Booking> bookings);
}