package com.service.bookinghotels.validation.hotel;
import com.service.bookinghotels.web.dto.hotel.HotelFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class HotelFilterValidValidator implements ConstraintValidator<HotelFilterValid, HotelFilter> {

    @Override
    public boolean isValid(HotelFilter filter, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(filter.getPage(), filter.getSize());
    }
}