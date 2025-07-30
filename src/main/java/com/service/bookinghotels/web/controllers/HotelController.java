package com.service.bookinghotels.web.controllers;
import com.service.bookinghotels.mappers.hotel.HotelMapper;
import com.service.bookinghotels.services.HotelService;
import com.service.bookinghotels.web.dto.hotel.HotelFilter;
import com.service.bookinghotels.web.dto.hotel.HotelRequest;
import com.service.bookinghotels.web.dto.hotel.HotelResponse;
import com.service.bookinghotels.web.dto.hotel.HotelsListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    private final HotelMapper hotelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public HotelResponse createHotel(@RequestBody HotelRequest hotelRequest) {
       return hotelMapper.hotelToHotelResponse(hotelService
               .createHotel(hotelMapper.hotelRequestToHotel(hotelRequest)));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public HotelResponse findHotelById(@PathVariable Long id) {
        return hotelMapper.hotelToHotelResponse(hotelService
                .getHotelById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public HotelResponse updateHotel(@PathVariable Long id, @RequestBody HotelRequest hotelRequest) {
        return hotelMapper.hotelToHotelResponse(hotelService
                .updateHotel(id, hotelMapper.hotelRequestToHotel(id, hotelRequest)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public HotelsListResponse findAllHotels(@Valid HotelFilter filter) {
        return hotelMapper.hotelListToHotelsListResponse(hotelService.getAllHotels(filter));
    }
}