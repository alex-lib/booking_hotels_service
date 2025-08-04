package com.service.bookinghotels.web.dto.booking;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingsListResponse {

    private List<BookingResponse> bookings;
}