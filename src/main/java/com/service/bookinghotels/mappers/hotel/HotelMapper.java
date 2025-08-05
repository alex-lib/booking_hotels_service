package com.service.bookinghotels.mappers.hotel;
import com.service.bookinghotels.entities.Hotel;
import com.service.bookinghotels.web.dto.hotel.HotelRequest;
import com.service.bookinghotels.web.dto.hotel.HotelResponse;
import com.service.bookinghotels.web.dto.hotel.HotelsListResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@DecoratedWith(HotelMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {

    Hotel hotelRequestToHotel(HotelRequest hotelRequest);

    @Mapping(source = "hotelId", target = "id")
    Hotel hotelRequestToHotel(Long hotelId, HotelRequest hotelRequest);

    HotelResponse hotelToHotelResponse(Hotel hotel);

    default HotelsListResponse hotelListToHotelsListResponse(List<Hotel> hotels) {
        List<HotelResponse> list = hotels.stream()
                .map(this::hotelToHotelResponse)
                .toList();
        return HotelsListResponse.builder()
                .hotels(list)
                .build();
    }
}