package com.example.springboot_010.api.OrderService;

import com.example.springboot_010.api.UserClient.UserClient;
import com.example.springboot_010.api.UserDto.UserDto;
import com.example.springboot_010.api.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final UserClient userClient;

    public OrderService(UserClient userClient) {
        this.userClient = userClient;
    }

    public OrderResponse createOrder(Long userId) {
        UserDto user = userClient.getUserById(userId);
        return new OrderResponse("Order Created", user.getName());
    }
}
