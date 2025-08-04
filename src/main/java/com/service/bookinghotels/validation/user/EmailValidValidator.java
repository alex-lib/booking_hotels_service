package com.service.bookinghotels.validation.user;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidValidator implements ConstraintValidator<EmailValid, String> {

    private static final String REGEX = "[0-9a-zA-Z_-]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.matches(REGEX);
    }
}
