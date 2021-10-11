package com.example.aggregator.client;

import com.example.aggregator.dto.PaymentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "data")
public interface DataClient {
  @GetMapping
  List<PaymentResponseDto> getValue(@RequestHeader("Authn") long id);

  @GetMapping("/admin")
  List<PaymentResponseDto> getAll();
}
