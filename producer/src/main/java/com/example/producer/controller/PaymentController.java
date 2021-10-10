package com.example.producer.controller;

import com.example.producer.dto.PaymentRequestDto;
import com.example.producer.dto.PaymentResponseDto;
import com.example.producer.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping("/payments")
    public PaymentResponseDto payment(@RequestBody @Valid PaymentRequestDto payment, @RequestHeader("Authn") long id){
        return service.proceedPayment(payment, id);
    }
}
