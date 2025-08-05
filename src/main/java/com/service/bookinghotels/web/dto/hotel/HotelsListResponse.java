package com.service.bookinghotels.web.dto.hotel;
import com.service.bookinghotels.repositories.HotelRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelsListResponse {

   @Autowired
   private HotelRepository hotelRepository;

   private List<HotelResponse> hotels;

   private Integer totalCountHotels = hotelRepository.findAll().size();
}