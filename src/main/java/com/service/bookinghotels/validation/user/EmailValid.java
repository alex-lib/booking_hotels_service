package com.service.bookinghotels.validation.user;
import jakarta.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValid {
}