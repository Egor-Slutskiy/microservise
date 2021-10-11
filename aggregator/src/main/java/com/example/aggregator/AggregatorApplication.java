package com.example.aggregator;

import com.example.aggregator.client.DataClient;
import com.example.aggregator.client.UsersClient;
import com.example.aggregator.dto.PaymentResponseDto;
import com.example.aggregator.dto.UserWithPaymentsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@RestController
@RequiredArgsConstructor
@CommonsLog
public class AggregatorApplication {
  private final DiscoveryClient discoveryClient;
  private final DataClient dataClient;
  private final UsersClient usersClient;

  @GetMapping("/api/payments")
  public List<UserWithPaymentsDto> value(@RequestHeader("Authn") long id) {
    final var payments = dataClient.getValue(id);
    final var user = usersClient.getValue(id);
    return List.of(new UserWithPaymentsDto(user,payments));
  }

  public static void main(String[] args) {
    SpringApplication.run(AggregatorApplication.class, args);
  }
}
