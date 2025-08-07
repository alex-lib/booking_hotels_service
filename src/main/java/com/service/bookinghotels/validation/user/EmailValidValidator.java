package com.service.bookinghotels.validation.user;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidValidator implements ConstraintValidator<EmailValid, String> {

    private static final String REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.matches(REGEX);
    }
}