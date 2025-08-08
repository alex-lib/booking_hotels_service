package com.service.bookinghotels.entities.statistics;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.time.LocalDate;
import org.springframework.data.annotation.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "statistics")
public class StatisticEvent {

    @Id
    private String id;

    private EventType eventType;

    private Long userId;

    private String userName;

    private String email;

    private Long roomId;

    private Long bookingId;

    private Instant createdAt;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;
}