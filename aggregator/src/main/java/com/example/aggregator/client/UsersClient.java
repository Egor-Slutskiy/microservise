package com.example.aggregator.client;

import com.example.aggregator.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "users")
public interface UsersClient {
    @GetMapping
    UserResponseDto getValue(@RequestHeader("Authn") long id);

    @PostMapping("/admin")
    List<UserResponseDto> getAll(@RequestBody List<Long> idList);
}
