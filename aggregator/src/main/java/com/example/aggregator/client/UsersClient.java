package com.example.aggregator.client;

import com.example.aggregator.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "users")
public interface UsersClient {
    @GetMapping
    UserResponseDto getValue();
}
