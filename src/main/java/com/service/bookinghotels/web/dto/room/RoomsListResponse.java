package com.service.bookinghotels.web.dto.room;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomsListResponse {

    private List<RoomResponse> rooms;
}