package com.example.producer.dto;

import com.example.producer.validation.CardNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequestDto {

    @NotNull
    @Min(1)
    private long amount;
    @NotNull
    @CardNumber
    private long cardNumber;
    @Size(max=100)
    private String comment;
}
