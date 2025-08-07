package com.service.bookinghotels.web.dto.room;
import com.service.bookinghotels.validation.room.RoomFilterValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@RoomFilterValid
@Getter
@Setter
@NoArgsConstructor
public class RoomFilter {

    private Integer pageNumber;

    private Integer pageSize;

    private Long roomId;

    private String roomName;

    private Long hotelId;

    private Integer peoplePerRoom;

    private BigDecimal maxPrice;

    private BigDecimal minPrice;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;
}