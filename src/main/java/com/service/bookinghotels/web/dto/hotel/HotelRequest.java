package com.service.bookinghotels.web.dto.hotel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRequest {

    private String name;

    private String title;

    private String city;

    private String address;

    private Double distanceFromCityCentre;
}