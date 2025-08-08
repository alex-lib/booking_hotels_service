package com.service.bookinghotels.services;
import com.service.bookinghotels.entities.statistics.StatisticEvent;

public interface StatisticService {

    void saveStatistic(StatisticEvent event);

    byte[] exportAllStatisticsToCsv();

    byte[] exportRegistrationUsersStatisticsToCsv();

    byte[] exportBookingRoomsStatisticsToCsv();
}