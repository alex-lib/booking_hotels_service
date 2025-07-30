package com.service.bookinghotels.web.dto.room;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {

    private String name;

    private String description;

    private Integer number;

    private BigDecimal price;

    private Integer maxPeoplePerRoom;

    private Set<LocalDate> busyDates;

    private Long hotelId;
}