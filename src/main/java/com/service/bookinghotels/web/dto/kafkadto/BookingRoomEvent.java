package com.service.bookinghotels.web.dto.kafkadto;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookingRoomEvent {

    private Long bookingId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long userId;

    private Long roomId;
}