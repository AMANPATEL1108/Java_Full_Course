package com.example.SpringBoot_06.api.service;
import org.springframework.security.core.userdetails.UserDetails;
public interface AuthUserDetailsService {
    UserDetails loadUserByUsername(String username);
}