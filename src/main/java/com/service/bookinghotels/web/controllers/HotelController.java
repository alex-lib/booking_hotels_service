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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    private final HotelMapper hotelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HotelResponse createHotel(@RequestBody HotelRequest hotelRequest) {
       return hotelMapper.hotelToHotelResponse(hotelService
               .createHotel(hotelMapper.hotelRequestToHotel(hotelRequest)));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public HotelResponse findHotelById(@PathVariable Long id) {
        return hotelMapper.hotelToHotelResponse(hotelService
                .getHotelById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HotelResponse updateHotel(@PathVariable Long id,
                                     @RequestBody HotelRequest hotelRequest) {
        return hotelMapper.hotelToHotelResponse(hotelService
                .updateHotel(id, hotelMapper.hotelRequestToHotel(id, hotelRequest)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteBook(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find-all")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public HotelsListResponse findAllHotels(@Valid HotelFilter filter) {
        return hotelMapper.hotelListToHotelsListResponse(hotelService.getAllHotels(filter));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/set-rating/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public void updateHotelRating(@PathVariable Long id, @RequestParam Integer rating) {
        hotelService.updateHotelRating(id, rating);
    }
}