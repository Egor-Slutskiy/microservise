package com.example.aggregator;

import com.example.aggregator.client.DataClient;
import com.example.aggregator.client.UsersClient;
import com.example.aggregator.dto.PaymentResponseDto;
import com.example.aggregator.dto.UserResponseDto;
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

import java.util.ArrayList;
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
  public List<UserWithPaymentsDto> value(@RequestHeader("Authn") long id,
                                         @RequestHeader("X-Role") String role) {
    if(role.equals("ROLE_USER")){
      final var payments = dataClient.getValue(id);
      final var user = usersClient.getValue(id);
      return List.of(new UserWithPaymentsDto(user,payments));
    }
    if(role.equals("ROLE_ADMIN")){
      final var payments = dataClient.getAll();
      List<Long> idList = new ArrayList<>();
      for(PaymentResponseDto payment:payments){
        if(!idList.contains(payment.getSender_id())){
          idList.add(payment.getSender_id());
        }
      }
      final var users = usersClient.getAll(idList);
      final List<UserWithPaymentsDto> userWithPaymentsDtos = new ArrayList<>();
      for(UserResponseDto user:users){
        List<PaymentResponseDto> userPayments = new ArrayList<>();
        for(PaymentResponseDto payment:payments){
          if(user.getId() == payment.getSender_id()){
            userPayments.add(payment);
          }
        }
        userWithPaymentsDtos.add(new UserWithPaymentsDto(user, userPayments));
      }
      return userWithPaymentsDtos;
    }
    throw new UnsupportedOperationException("Invalid role");
  }

  public static void main(String[] args) {
    SpringApplication.run(AggregatorApplication.class, args);
  }
}
