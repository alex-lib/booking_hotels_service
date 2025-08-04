package com.service.bookinghotels.web.dto.user;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String name;

    private String email;
}
