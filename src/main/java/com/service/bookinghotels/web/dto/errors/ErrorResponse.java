package com.service.bookinghotels.web.dto.errors;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {

    private String errorMessage;
}