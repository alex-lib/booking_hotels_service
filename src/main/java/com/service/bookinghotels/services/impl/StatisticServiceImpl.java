package com.service.bookinghotels.services.impl;
import com.service.bookinghotels.entities.statistics.EventType;
import com.service.bookinghotels.entities.statistics.StatisticEvent;
import com.service.bookinghotels.repositories.StatisticRepository;
import com.service.bookinghotels.services.StatisticService;
import com.service.bookinghotels.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    private final UserService userService;

    @Override
    public void saveStatistic(StatisticEvent event) {
        log.info("Call method saveStatistic for event: {}", event);
        statisticRepository.save(event);
    }

    @Override
    public byte[] exportAllStatisticsToCsv() {
        log.info("Call method exportAllStatisticsToCsv");
        List<StatisticEvent> events = statisticRepository.findAll();
        return convertToCsv(events, "ALL");
    }

    @Override
    public byte[] exportRegistrationUsersStatisticsToCsv() {
        log.info("Call method exportRegistrationUsersStatisticsToCsv");
        List<StatisticEvent> events = statisticRepository.findByEventType(EventType.REGISTRATION_USER);
        return convertToCsv(events, "REGISTRATION_USER");
    }

    @Override
    public byte[] exportBookingRoomsStatisticsToCsv() {
        log.info("Call method exportBookingRoomsStatisticsToCsv");
        List<StatisticEvent> events = statisticRepository.findByEventType(EventType.BOOKING_ROOM);
        return convertToCsv(events, "BOOKING_ROOM");
    }

    private byte[] convertToCsv(List<StatisticEvent> events, String type) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8))) {
            writeCsvHeaders(writer, type);
            for (StatisticEvent event : events) {
                switch (type) {
                    case "ALL" -> writeAllEventData(writer, event);
                    case "REGISTRATION_USER" -> writeRegistrationUserEventData(writer, event);
                    case "BOOKING_ROOM" -> writeBookingEventData(writer, event);
                }
            }
            writer.flush();
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
    }

    private void writeCsvHeaders(PrintWriter writer, String type) {
        switch (type) {
            case "ALL" -> writer.println("Statistic ID,Event Type,User ID,User name,email,Room ID,Booking ID,Created At,Check-In Date,Check-Out Date");
            case "REGISTRATION_USER" -> writer.println("Statistic ID,User ID,User name,email,Created At");
            case "BOOKING_ROOM" -> writer.println("Statistic ID,User ID,Room ID,Booking ID,Created At,Check-In Date,Check-Out Date");
        }
    }

    private void writeAllEventData(PrintWriter writer, StatisticEvent event) {
        writer.println(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                escapeCsvField(event.getId()),
                escapeCsvField(String.valueOf(event.getEventType())),
                escapeCsvField(String.valueOf(event.getUserId())),
                escapeCsvField(event.getUserName()),
                escapeCsvField(event.getEmail()),
                escapeCsvField(String.valueOf(event.getRoomId())),
                escapeCsvField(String.valueOf(event.getBookingId())),
                escapeCsvField(event.getCreatedAt().toString()),
                escapeCsvField(event.getCheckInDate() == null ? "" : event.getCheckInDate().toString()),
                escapeCsvField(event.getCheckOutDate() == null ? "" : event.getCheckOutDate().toString())
        ));
    }

    private void writeRegistrationUserEventData(PrintWriter writer, StatisticEvent event) {
        writer.println(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                escapeCsvField(event.getId()),
                escapeCsvField(String.valueOf(event.getUserId())),
                escapeCsvField(event.getUserName()),
                escapeCsvField(event.getEmail()),
                escapeCsvField(event.getCreatedAt().toString())
        ));
    }

    private void writeBookingEventData(PrintWriter writer, StatisticEvent event) {
        writer.println(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                escapeCsvField(event.getId()),
                escapeCsvField(String.valueOf(event.getUserId())),
                escapeCsvField(String.valueOf(event.getRoomId())),
                escapeCsvField(String.valueOf(event.getBookingId())),
                escapeCsvField(event.getCreatedAt().toString()),
                escapeCsvField(event.getCheckInDate().toString()),
                escapeCsvField(event.getCheckOutDate().toString())
        ));
    }

    private String escapeCsvField(String field) {
        if (field == null) return "";
        return field.replace("\"", "\"\"");
    }
}