package com.example.Databases_System_Design_015.user.service.impl;

import com.example.Databases_System_Design_015.user.dto.UserRequest;
import com.example.Databases_System_Design_015.user.dto.UserResponse;
import com.example.Databases_System_Design_015.user.entity.User;
import com.example.Databases_System_Design_015.user.repository.UserRepository;
import com.example.Databases_System_Design_015.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken: " + request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .displayName(request.getDisplayName() != null ? request.getDisplayName() : request.getUsername())
                .bio(request.getBio())
                .profilePictureUrl(request.getProfilePictureUrl())
                .bannerUrl(request.getBannerUrl())
                .location(request.getLocation())
                .website(request.getWebsite())
                .isVerified(false)
                .isActive(true)
                .followersCount(0L)
                .followingCount(0L)
                .tweetsCount(0L)
                .build();
        return UserResponse.from(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        return UserResponse.from(findUserOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: @" + username));
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
    public List<UserResponse> searchUsers(String keyword) {
        return userRepository
                .findByUsernameContainingIgnoreCaseOrDisplayNameContainingIgnoreCase(keyword, keyword)
                .stream().map(UserResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getTopUsers() {
        return userRepository.findTopUsersByFollowers().stream()
                .map(UserResponse::from).collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = findUserOrThrow(id);
        user.setDisplayName(request.getDisplayName());
        user.setBio(request.getBio());
        user.setProfilePictureUrl(request.getProfilePictureUrl());
        user.setBannerUrl(request.getBannerUrl());
        user.setLocation(request.getLocation());
        user.setWebsite(request.getWebsite());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(request.getPassword());
        }
        return UserResponse.from(userRepository.save(user));
    }

    @Override
    public void deactivateUser(Long id) {
        User user = findUserOrThrow(id);
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}