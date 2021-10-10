package com.example.producer.controller;

import com.example.producer.dto.PaymentRequestDto;
import com.example.producer.dto.PaymentResponseDto;
import com.example.producer.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/producer")
@Validated
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping("/api/payments")
    public PaymentResponseDto payment(@RequestBody @Valid PaymentRequestDto payment){
        return service.proceedPayment(payment);
    }
}
