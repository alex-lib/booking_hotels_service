package com.service.bookinghotels.web.dto.booking;
import com.service.bookinghotels.web.dto.room.RoomResponse;
import com.service.bookinghotels.web.dto.user.UserResponse;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private UserResponse userResponse;

    private RoomResponse roomResponse;
}