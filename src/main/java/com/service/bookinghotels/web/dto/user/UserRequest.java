package com.service.bookinghotels.web.dto.user;
import com.service.bookinghotels.validation.user.EmailValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = "Name must be specified")
    @Size(min = 2, max = 20, message = "Name must contain between {min} and {max} characters")
    private String name;

    @NotBlank(message = "Password must be specified")
    @Size(min = 4, max = 10, message = "Password must contain between {min} and {max} characters")
    private String password;

    @NotBlank(message = "Email must be specified")
    @EmailValid
    private String email;
}
