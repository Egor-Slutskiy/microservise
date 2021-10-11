package com.example.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserWithRoleResponseDto {
    private long id;
    private String username;
    private String role;
}
