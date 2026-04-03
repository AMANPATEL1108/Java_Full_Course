package com.example.Databases_System_Design_016.user.service.impl;

import com.example.Databases_System_Design_016.user.dto.UserRequest;
import com.example.Databases_System_Design_016.user.dto.UserResponse;
import com.example.Databases_System_Design_016.user.entity.User;
import com.example.Databases_System_Design_016.user.repository.UserRepository;
import com.example.Databases_System_Design_016.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse registerUser(UserRequest request) {
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already registered: " + request.getPhoneNumber());
        }
        User user = User.builder()
                .phoneNumber(request.getPhoneNumber())
                .displayName(request.getDisplayName() != null
                        ? request.getDisplayName()
                        : request.getPhoneNumber())
                .about(request.getAbout())
                .profilePictureUrl(request.getProfilePictureUrl())
                .onlineStatus(User.OnlineStatus.OFFLINE)
                .isActive(true)
                .build();
        return UserResponse.from(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        return UserResponse.from(findOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found: " + phoneNumber));
        return UserResponse.from(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> searchUsersByName(String name) {
        return userRepository.findByDisplayNameContainingIgnoreCase(name).stream()
                .map(UserResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getUsersByOnlineStatus(User.OnlineStatus status) {
        return userRepository.findByOnlineStatus(status).stream()
                .map(UserResponse::from).collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = findOrThrow(id);
        if (request.getDisplayName() != null) user.setDisplayName(request.getDisplayName());
        if (request.getAbout() != null) user.setAbout(request.getAbout());
        if (request.getProfilePictureUrl() != null) user.setProfilePictureUrl(request.getProfilePictureUrl());
        return UserResponse.from(userRepository.save(user));
    }

    @Override
    public UserResponse updateOnlineStatus(Long id, User.OnlineStatus status) {
        User user = findOrThrow(id);
        user.setOnlineStatus(status);
        if (status == User.OnlineStatus.OFFLINE) {
            user.setLastSeen(LocalDateTime.now());
        }
        return UserResponse.from(userRepository.save(user));
    }

    @Override
    public void deactivateUser(Long id) {
        User user = findOrThrow(id);
        user.setIsActive(false);
        user.setOnlineStatus(User.OnlineStatus.OFFLINE);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private User findOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}