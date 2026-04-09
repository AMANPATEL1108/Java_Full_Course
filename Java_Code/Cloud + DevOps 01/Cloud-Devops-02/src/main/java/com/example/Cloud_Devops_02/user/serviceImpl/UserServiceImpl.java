package com.example.Cloud_Devops_02.user.serviceImpl;

import com.example.Cloud_Devops_02.user.dto.Request.UserRequest;
import com.example.Cloud_Devops_02.user.dto.Response.UserResponse;
import com.example.Cloud_Devops_02.user.model.User;
import com.example.Cloud_Devops_02.user.repository.UserRepository;
import com.example.Cloud_Devops_02.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserResponse createUser(UserRequest request) {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        return "";
    }

    @Override
    public List<UserResponse> getAllUser() {
        return List.of();
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return Optional.empty();
    }
}
