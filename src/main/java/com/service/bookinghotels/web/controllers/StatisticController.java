package com.service.bookinghotels.web.controllers;
import com.service.bookinghotels.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/export-all-statistics-to-csv", produces = "text/csv")
    @PreAuthorize("hasRole('ADMIN')")
    public byte[] exportAllStatisticsToCsv() {
        return statisticService.exportAllStatisticsToCsv();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/export-registration-users-statistics-to-csv", produces = "text/csv")
    @PreAuthorize("hasRole('ADMIN')")
    public byte[] exportRegistrationUsersStatisticsToCsv() {
        return statisticService.exportRegistrationUsersStatisticsToCsv();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/export-booking-rooms-statistics-to-csv", produces = "text/csv")
    @PreAuthorize("hasRole('ADMIN')")
    public byte[] exportBookingRoomsStatisticsToCsv() {
        return statisticService.exportBookingRoomsStatisticsToCsv();
    }
}