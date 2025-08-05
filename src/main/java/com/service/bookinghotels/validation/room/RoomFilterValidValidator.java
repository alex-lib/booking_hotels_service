package com.service.bookinghotels.validation.room;
import com.service.bookinghotels.web.dto.room.RoomFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class RoomFilterValidValidator implements ConstraintValidator<RoomFilterValid, RoomFilter> {
    @Override
    public boolean isValid(RoomFilter filter, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(filter.getPage(), filter.getSize())
                || (filter.getCheckOutDate() != null && filter.getCheckInDate() == null)
                || (filter.getCheckInDate() != null && filter.getCheckOutDate() == null);
    }
}
