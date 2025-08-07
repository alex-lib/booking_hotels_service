package com.service.bookinghotels.services;
import com.service.bookinghotels.entities.User;
import com.service.bookinghotels.entities.roles.RoleType;

public interface UserService {

    User getUserById(Long id);

    User createUser(User user, RoleType roleType);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User getUserByName(String name);

    User getUserByEmail(String email);
}