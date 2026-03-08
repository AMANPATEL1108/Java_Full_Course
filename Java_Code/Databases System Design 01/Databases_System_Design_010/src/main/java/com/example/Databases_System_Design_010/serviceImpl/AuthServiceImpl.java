package com.example.Databases_System_Design_010.serviceImpl;


import com.example.Databases_System_Design_010.dto.request.LoginRequest;
import com.example.Databases_System_Design_010.dto.request.RegisterRequest;
import com.example.Databases_System_Design_010.dto.response.AuthResponse;
import com.example.Databases_System_Design_010.repository.UserRepository;
import com.example.Databases_System_Design_010.service.AuthService;
import com.example.Databases_System_Design_010.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        // TODO: implement
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}