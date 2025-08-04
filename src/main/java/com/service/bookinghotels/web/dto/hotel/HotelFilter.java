package com.service.bookinghotels.web.dto.hotel;
import com.service.bookinghotels.validation.hotel.HotelFilterValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@HotelFilterValid
@Getter
@Setter
@NoArgsConstructor
public class HotelFilter {

    private Integer page;

    private Integer size;

    private Long id;

    private String title;

    private String name;

    private String city;

    private String address;

    private Double distanceFromCityCentre;

    private Double rating;

    private Integer gradesCount;
}