package com.service.bookinghotels.services;
import com.service.bookinghotels.entities.Hotel;
import com.service.bookinghotels.web.dto.hotel.HotelFilter;
import java.util.List;

public interface HotelService {

    Hotel getHotelById(Long id);

    Hotel createHotel(Hotel hotel);

    Hotel updateHotel(Long id, Hotel hotel);

    void deleteHotel(Long id);

    List<Hotel> getAllHotels(HotelFilter filter);

    Hotel getHotelByName(String name);
}