package com.example.aggregator.client;

import com.example.aggregator.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "users")
public interface UsersClient {
    @GetMapping
    UserResponseDto getValue(@RequestHeader("Authn") long id);
}
