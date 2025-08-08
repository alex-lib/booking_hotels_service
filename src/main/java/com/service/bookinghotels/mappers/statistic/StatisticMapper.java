package com.service.bookinghotels.mappers.statistic;
import com.service.bookinghotels.entities.statistics.StatisticEvent;
import com.service.bookinghotels.web.dto.kafkadto.BookingRoomEvent;
import com.service.bookinghotels.web.dto.kafkadto.RegistrationUserEvent;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(StatisticMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatisticMapper {

   StatisticEvent bookingRoomEventToStatisticEvent(BookingRoomEvent event);

   StatisticEvent registrationUserEventToStatisticEvent(RegistrationUserEvent event);
}