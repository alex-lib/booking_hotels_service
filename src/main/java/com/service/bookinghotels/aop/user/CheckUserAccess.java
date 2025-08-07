package com.service.bookinghotels.aop.user;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckUserAccess {

    String message() default "Unauthorized access";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}