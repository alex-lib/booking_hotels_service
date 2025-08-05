package com.service.bookinghotels.services.impl;
import com.service.bookinghotels.entities.Room;
import com.service.bookinghotels.entities.UnavailableDate;
import com.service.bookinghotels.repositories.UnavailableDateRepository;
import com.service.bookinghotels.services.RoomService;
import com.service.bookinghotels.services.UnavailableDatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnavailableDatesServiceImpl implements UnavailableDatesService {

    private final UnavailableDateRepository unavailableDateRepository;

    private final RoomService roomService;

    @Transactional
    @Override
    public void addBusyDates(Long roomId, Set<LocalDate> datesToLive) {
        log.info("Call method addBusyDates to add busy dates to room with id: {}", roomId);
        Room room = roomService.getRoomById(roomId);
        for (LocalDate date : datesToLive) {
            UnavailableDate unavailableDate = new UnavailableDate();
            unavailableDate.setDate(date);
            unavailableDate.setRoom(room);
            unavailableDateRepository.save(unavailableDate);
        }
    }

    @Transactional
    @Override
    public void deleteBusyDates(Long roomId, Set<LocalDate> datesToDelete) {
        log.info("Call method deleteBusyDates to delete busy dates from room with id: {}", roomId);
        Room room = roomService.getRoomById(roomId);
        for (LocalDate date : datesToDelete) {
            unavailableDateRepository.deleteByRoomAndDate(room, date);
        }
    }
}
