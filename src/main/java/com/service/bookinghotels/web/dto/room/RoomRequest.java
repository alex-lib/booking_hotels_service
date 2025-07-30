package com.service.bookinghotels.web.dto.room;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequest {

    private String name;

    private String description;

    private Integer number;

    private BigDecimal price;

    private Integer maxPeoplePerRoom;

    private Long hotelId;
}