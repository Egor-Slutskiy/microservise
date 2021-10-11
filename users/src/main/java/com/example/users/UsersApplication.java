package com.example.users;

import com.example.users.dto.JwtResponseDto;
import com.example.users.dto.UserResponseDto;
import com.example.users.dto.UserWithRoleResponseDto;
import com.example.users.entity.UserEntity;
import com.example.users.repository.UserRepository;
import com.example.users.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
@Transactional
public class UsersApplication {

    private final UserRepository repository;
    private final JwtGenerator jwtGenerator;

    @GetMapping
    public UserResponseDto endpoint(@RequestHeader("Authn") long id) {
        final Optional<UserEntity> user = repository.findById(id);
        if(user.isPresent()){
            final var userEntity = user.get();
            return new UserResponseDto(userEntity.getId(), userEntity.getUsername());
        }
        return new UserResponseDto();
    }

    @PostMapping("/admin")
    public List<UserResponseDto> getAll(@RequestBody List<Long> idList){
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(long id:idList){
            final Optional<UserEntity> user = repository.findById(id);
            if(user.isPresent()){
                final var userEntity = user.get();
                userResponseDtos.add(new UserResponseDto(userEntity.getId(), userEntity.getUsername()));
            }
        }
        return userResponseDtos;
    }

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

    @PostMapping("/api/user")
    public JwtResponseDto saveUser(@RequestBody UserWithRoleResponseDto user){
        final var userEntity = new UserEntity(user.getId(), user.getUsername(), user.getRole());
        repository.save(userEntity);
        return new JwtResponseDto(jwtGenerator.generate(userEntity));
    }

}
