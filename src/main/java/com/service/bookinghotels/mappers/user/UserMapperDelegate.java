package com.service.bookinghotels.mappers.user;
import com.service.bookinghotels.entities.User;
import com.service.bookinghotels.web.dto.user.UserRequest;
import com.service.bookinghotels.web.dto.user.UserResponse;

public abstract class UserMapperDelegate implements UserMapper {

    @Override
    public User userRequestToUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail().toLowerCase())
                .build();
    }

    @Override
    public User userRequestToUser(Long userId, UserRequest userRequest) {
        User user = userRequestToUser(userRequest);
        user.setId(userId);
        return user;
    }

    @Override
    public UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
