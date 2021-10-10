package com.example.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserWithPaymentsDto {
    private UserResponseDto user;
    private List<PaymentResponseDto> payments;
}
