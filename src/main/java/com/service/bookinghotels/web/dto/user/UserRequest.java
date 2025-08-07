package com.service.bookinghotels.web.dto.user;
import com.service.bookinghotels.validation.user.EmailValid;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @Size(min = 2, max = 20, message = "Name must contain between {min} and {max} characters")
    private String name;

    @Size(min = 4, max = 10, message = "Password must contain between {min} and {max} characters")
    private String password;

    @EmailValid
    private String email;
}