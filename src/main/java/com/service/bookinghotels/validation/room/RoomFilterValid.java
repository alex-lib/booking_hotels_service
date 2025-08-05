package com.service.bookinghotels.validation.room;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoomFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoomFilterValid {

    String message() default "Fields of pagination must be pointed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}