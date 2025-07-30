package com.service.bookinghotels.validation.hotel;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HotelFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HotelFilterValid {

    String message() default "Fields of pagination must be pointed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}