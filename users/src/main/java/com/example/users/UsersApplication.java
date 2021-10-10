package com.example.users;

import com.example.users.dto.UserResponseDto;
import com.example.users.entity.UserEntity;
import com.example.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
@Transactional
public class UsersApplication {

    private final UserRepository repository;

    @GetMapping
    public UserResponseDto endpoint(@RequestHeader Optional<String> authorization) {
        // TODO: userId
        final Optional<UserEntity> user = repository.findById(2L);
        if(user.isPresent()){
            final var userEntity = user.get();
            return new UserResponseDto(userEntity.getId(), userEntity.getUsername());
        }
        return new UserResponseDto();
    }

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

    @PostMapping("/api/user")
    public void saveUser(@RequestBody UserResponseDto user){
        repository.save(new UserEntity(user.getId(), user.getUsername()));
    }

}
