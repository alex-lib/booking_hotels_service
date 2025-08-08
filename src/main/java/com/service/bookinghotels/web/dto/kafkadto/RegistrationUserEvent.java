package com.service.bookinghotels.web.dto.kafkadto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegistrationUserEvent {

    private Long userId;

    private String userName;

    private String email;
}