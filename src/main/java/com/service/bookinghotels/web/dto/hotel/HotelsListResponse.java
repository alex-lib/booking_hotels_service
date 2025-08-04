package com.service.bookinghotels.web.dto.hotel;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelsListResponse {

   private List<HotelResponse> hotels;
}