package com.service.bookinghotels.mappers.statistic;
import com.service.bookinghotels.entities.User;
import com.service.bookinghotels.entities.statistics.EventType;
import com.service.bookinghotels.entities.statistics.StatisticEvent;
import com.service.bookinghotels.services.UserService;
import com.service.bookinghotels.web.dto.kafkadto.BookingRoomEvent;
import com.service.bookinghotels.web.dto.kafkadto.RegistrationUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Instant;

public abstract class StatisticMapperDelegate implements StatisticMapper {

    @Autowired
    private UserService userService;

    @Override
    public StatisticEvent registrationUserEventToStatisticEvent(RegistrationUserEvent event) {
        return StatisticEvent.builder()
                .eventType(EventType.REGISTRATION_USER)
                .createdAt(Instant.now())
                .userId(event.getUserId())
                .userName(event.getUserName())
                .email(event.getEmail())
                .build();
    }

    @Override
    public StatisticEvent bookingRoomEventToStatisticEvent(BookingRoomEvent event) {
        User user = userService.getUserById(event.getUserId());
        return StatisticEvent.builder()
                .eventType(EventType.BOOKING_ROOM)
                .createdAt(Instant.now())
                .bookingId(event.getBookingId())
                .checkInDate(event.getCheckInDate())
                .checkOutDate(event.getCheckOutDate())
                .roomId(event.getRoomId())
                .userId(event.getUserId())
                .userName(user.getName())
                .email(user.getEmail())
                .build();
    }
}