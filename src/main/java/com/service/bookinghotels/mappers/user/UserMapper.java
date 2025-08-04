package com.service.bookinghotels.mappers.user;
import com.service.bookinghotels.entities.User;
import com.service.bookinghotels.web.dto.user.UserRequest;
import com.service.bookinghotels.web.dto.user.UserResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(UserMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User userRequestToUser(UserRequest userRequest);

    @Mapping(source = "userId", target = "id")
    User userRequestToUser(Long userId, UserRequest userRequest);

    UserResponse userToUserResponse(User user);
}