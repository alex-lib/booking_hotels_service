package com.service.bookinghotels.services.impl;
import com.service.bookinghotels.entities.Hotel;
import com.service.bookinghotels.exceptions.EntityIsExistedException;
import com.service.bookinghotels.exceptions.EntityNotFoundException;
import com.service.bookinghotels.repositories.HotelRepository;
import com.service.bookinghotels.services.HotelService;
import com.service.bookinghotels.services.HotelSpecification;
import com.service.bookinghotels.utils.BeanUtils;
import com.service.bookinghotels.web.dto.hotel.HotelFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Transactional(readOnly = true)
    @Override
    public Hotel getHotelById(Long id) {
        log.info("Call method getHotelById to find hotel with id: {}", id);
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel is not found"));
    }

    @Transactional
    @Override
    public Hotel createHotel(Hotel hotel) {
        log.info("Call method createHotel to create hotel: {}", hotel);
        if (getHotelByName(hotel.getName()) != null) {
            throw new EntityIsExistedException("Such hotel's name is already existed");
        }
        return hotelRepository.save(hotel);
    }

    @Transactional
    @Override
    public Hotel updateHotel(Long id, Hotel hotel) {
        log.info("Call method updateHotel to update hotel: {}", hotel);
        Hotel existedHotel = getHotelById(id);
        BeanUtils.copyNonNullProperties(hotel, existedHotel);
        return hotelRepository.save(existedHotel);
    }

    @Transactional
    @Override
    public void deleteHotel(Long id) {
        log.info("Call method deleteHotel to delete hotel with id: {}", id);
        hotelRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Hotel> getAllHotels(HotelFilter filter) {
        log.info("Call method getAllHotels");
        return hotelRepository.findAll(HotelSpecification.withFilter(filter),
                PageRequest.of(filter.getPage(), filter.getSize())).getContent();
    }

    @Transactional(readOnly = true)
    @Override
    public Hotel getHotelByName(String name) {
        log.info("Call method getHotelByName to find hotel with name: {}", name);
        return hotelRepository.getHotelByName(name);
    }

    @Transactional
    @Override
    public void updateHotelRating(Long id, Integer newMark) {
        if (newMark < 1 || newMark > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        log.info("Call method updateHotelRating to update hotel rating: {} for hotel with id: {}", newMark, id);
        Hotel hotel = getHotelById(id);
        double totalRating = hotel.getRating() * hotel.getGradesCount();
        totalRating = totalRating - hotel.getRating() + newMark;
        double newRating = (double) Math.round((totalRating / hotel.getGradesCount() + 1) * 10) / 10;
        hotel.setGradesCount(hotel.getRooms().size() + 1);
        hotel.setRating(newRating);
        hotelRepository.save(hotel);
    }
}