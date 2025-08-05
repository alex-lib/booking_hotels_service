package com.service.bookinghotels.services.impl;
import com.service.bookinghotels.entities.Booking;
import com.service.bookinghotels.entities.Room;
import com.service.bookinghotels.exceptions.EntityNotFoundException;
import com.service.bookinghotels.exceptions.RoomIsBusyException;
import com.service.bookinghotels.repositories.BookingRepository;
import com.service.bookinghotels.repositories.RoomRepository;
import com.service.bookinghotels.services.BookingService;
import com.service.bookinghotels.services.UnavailableDatesService;
import com.service.bookinghotels.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    private final UnavailableDatesService unavailableDatesService;

    @Transactional
    @Override
    public Booking createBooking(Booking booking) {
        log.info("Call method createBooking to create booking: {}", booking);
        setNewBusyDatesForRoom(booking);
        return bookingRepository.save(booking);
    }

    @Transactional
    @Override
    public Booking updateBooking(Long id, Booking booking) {
        log.info("Call method updateBooking to update booking: {}", booking);
        Booking existedBooking = getBookingById(id);
        deletePreviousBusyDatesForRoom(existedBooking);
        setNewBusyDatesForRoom(booking);
        BeanUtils.copyNonNullProperties(booking, existedBooking);
        return bookingRepository.save(existedBooking);
    }

    @Transactional
    @Override
    public void deleteBooking(Long id) {
        log.info("Call method deleteBooking to delete booking with id: {}", id);
        Booking booking = getBookingById(id);
        deletePreviousBusyDatesForRoom(booking);
        bookingRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Booking getBookingById(Long id) {
        log.info("Call method getBookingById to find booking with id: {}", id);
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking is not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Booking> getAllBookings() {
        log.info("Call method getAllBookings");
        return bookingRepository.findAll();
    }

    private void setNewBusyDatesForRoom(Booking booking) {
        Room room = booking.getRoom();
        Set<LocalDate> datesToLive = new HashSet<>();
        for (LocalDate i = booking.getCheckInDate();
             !i.isEqual(booking.getCheckOutDate().plusDays(1));
             i.plusDays(1)) {
            datesToLive.add(i);
        }
        if (room.getBusyDates().stream().anyMatch(datesToLive::contains)) {
            log.error("Room is busy on dates: {}", datesToLive);
            throw new RoomIsBusyException(MessageFormat
                    .format("Room is busy on some or all of pointed dates: {1}. Total busy dates: {0}",
                            datesToLive, room.getBusyDates()));
        }
        unavailableDatesService.addBusyDates(room.getId(), datesToLive);
        roomRepository.save(room);
    }

    private void deletePreviousBusyDatesForRoom(Booking booking) {
        Room room = booking.getRoom();
        Set<LocalDate> datesToLiveBeforeUpdating = new HashSet<>();
        for (LocalDate i = booking.getCheckInDate();
             !i.isEqual(booking.getCheckOutDate().plusDays(1));
             i.plusDays(1)) {
            datesToLiveBeforeUpdating.add(i);
        }
        unavailableDatesService.deleteBusyDates(room.getId(), datesToLiveBeforeUpdating);
        roomRepository.save(room);
    }
}