package com.service.bookinghotels.services.impl;
import com.service.bookinghotels.entities.User;
import com.service.bookinghotels.entities.roles.Role;
import com.service.bookinghotels.entities.roles.RoleType;
import com.service.bookinghotels.exceptions.EntityIsExistedException;
import com.service.bookinghotels.exceptions.EntityNotFoundException;
import com.service.bookinghotels.repositories.UserRepository;
import com.service.bookinghotels.services.UserService;
import com.service.bookinghotels.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        log.info("Call method getUserById to find user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));
    }

    @Transactional
    @Override
    public User createUser(User user, RoleType roleType) {
        user.setRoles(Collections.singletonList(Role.from(roleType)));
        log.info("Call method createUser to create user: {}", user);
        if (getUserByName(user.getName()) != null) {
            throw new EntityIsExistedException("Such user's name is already existed");
        }
        if (isUserExistsByNameAndEmail(user.getName(), user.getEmail())) {
            throw new EntityIsExistedException("User with such email and name is already existed");
        }
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUser(Long id, User user) {
        log.info("Call method updateUser to update user: {}", user);
        User existedUser = getUserById(id);
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userRepository.save(existedUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        log.info("Call method deleteUser to delete user with id: {}", id);
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByName(String name) {
        log.info("Call method getUserByName to find user with name: {}", name);
        return userRepository.getUserByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUserExistsByNameAndEmail(String name, String email) {
        return userRepository.isUserExistsByNameAndEmail(name, email);
    }
}