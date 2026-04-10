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

        if (request.getEmail().isBlank() || request.getName().isBlank()) {
            throw new IllegalArgumentException("Name and Email Must be Required");
        }

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setName(request.getName());

        User saved = userRepository.save(newUser);

        return userResponse(saved);
    }

    @Override
    public String deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user Not Found"));

        userRepository.deleteById(id);
        return "User Deleted Successfully";
    }

    @Override
    public List<UserResponse> getAllUser() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::userResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        return Optional.of(userResponse(user));
    }

    UserResponse userResponse(User user) {
        UserResponse response = new UserResponse();
        response.setEmail(user.getEmail());
        response.setName(user.getName());

        return response;
    }
}
