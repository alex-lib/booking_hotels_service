package com.service.bookinghotels.mappers.hotel;
import com.service.bookinghotels.entities.Hotel;
import com.service.bookinghotels.web.dto.hotel.HotelRequest;
import com.service.bookinghotels.web.dto.hotel.HotelResponse;

public abstract class HotelMapperDelegate implements HotelMapper {

    @Override
    public Hotel hotelRequestToHotel(HotelRequest hotelRequest) {
        return Hotel.builder()
                .name(hotelRequest.getName())
                .title(hotelRequest.getTitle())
                .city(hotelRequest.getCity())
                .address(hotelRequest.getAddress())
                .distanceFromCityCentre(hotelRequest.getDistanceFromCityCentre())
                .build();
    }

    @Override
    public Hotel hotelRequestToHotel(Long hotelId, HotelRequest hotelRequest) {
        Hotel hotel = hotelRequestToHotel(hotelRequest);
        hotel.setId(hotelId);
        return hotel;
    }

    @Override
    public HotelResponse hotelToHotelResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .title(hotel.getTitle())
                .city(hotel.getCity())
                .address(hotel.getAddress())
                .distanceFromCityCentre(hotel.getDistanceFromCityCentre())
                .rating(hotel.getRating())
                .gradesCount(hotel.getGradesCount())
                .build();
    }
}