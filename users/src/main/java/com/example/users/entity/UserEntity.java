package com.example.users.entity;

import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(nullable = false, unique = true)
    private long id;
    @Column(nullable = false,unique = true)
    private String username;
}
