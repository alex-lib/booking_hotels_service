package com.service.bookinghotels.web.dto.booking;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long roomId;
}