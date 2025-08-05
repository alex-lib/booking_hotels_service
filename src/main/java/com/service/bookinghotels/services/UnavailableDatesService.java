package com.service.bookinghotels.services;
import java.time.LocalDate;
import java.util.Set;

public interface UnavailableDatesService {

    void addBusyDates(Long roomId, Set<LocalDate> datesToLive);

    void deleteBusyDates(Long roomId, Set<LocalDate> datesToLive);
}
