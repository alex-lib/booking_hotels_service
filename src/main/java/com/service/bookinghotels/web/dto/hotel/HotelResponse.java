package com.service.bookinghotels.web.dto.hotel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponse {

    private Long id;

    private String name;

    private String title;

    private String city;

    private String address;

    private Double distanceFromCityCentre;

    private Double rating;

    private Integer gradesCount;
}