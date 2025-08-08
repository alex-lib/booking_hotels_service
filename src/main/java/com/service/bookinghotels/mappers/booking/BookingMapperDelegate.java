package com.service.bookinghotels.mappers.booking;
import com.service.bookinghotels.entities.Booking;
import com.service.bookinghotels.entities.Room;
import com.service.bookinghotels.entities.User;
import com.service.bookinghotels.mappers.room.RoomMapper;
import com.service.bookinghotels.mappers.user.UserMapper;
import com.service.bookinghotels.services.RoomService;
import com.service.bookinghotels.services.UserService;
import com.service.bookinghotels.web.dto.booking.BookingRequest;
import com.service.bookinghotels.web.dto.booking.BookingResponse;
import com.service.bookinghotels.web.dto.kafkadto.BookingRoomEvent;
import com.service.bookinghotels.web.dto.room.RoomResponse;
import com.service.bookinghotels.web.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public abstract class BookingMapperDelegate implements BookingMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Booking bookingRequestToBooking(BookingRequest bookingRequest, UserDetails user) {
        User currentUser = userService.getUserByName(user.getUsername());
        Room room = roomService.getRoomById(bookingRequest.getRoomId());
        return Booking.builder()
                .user(currentUser)
                .room(room)
                .checkInDate(bookingRequest.getCheckInDate())
                .checkOutDate(bookingRequest.getCheckOutDate())
                .build();
    }

    @Override
    public Booking bookingRequestToBooking(Long bookingId, BookingRequest bookingRequest, UserDetails user) {
        Booking booking = bookingRequestToBooking(bookingRequest, user);
        booking.setId(bookingId);
        return booking;
    }

    @Override
    public BookingResponse bookingToBookingResponse(Booking booking) {
        UserResponse userResponse = userMapper.userToUserResponse(booking.getUser());
        RoomResponse roomResponse = roomMapper.roomToRoomResponse(booking.getRoom());
        return BookingResponse.builder()
                .id(booking.getId())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .userResponse(userResponse)
                .roomResponse(roomResponse)
                .build();
    }

    @Override
    public BookingRoomEvent bookingToBookingRoomEvent(Booking booking, UserDetails user) {
        return BookingRoomEvent.builder()
                .bookingId(booking.getId())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .userId(userService.getUserByName(user.getUsername()).getId())
                .roomId(booking.getRoom().getId())
                .build();
    }
}