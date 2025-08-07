package com.service.bookinghotels.aop.booking;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckBookingAccess {

    String message() default "Unauthorized access";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}