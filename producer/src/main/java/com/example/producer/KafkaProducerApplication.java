package com.example.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class KafkaProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaProducerApplication.class, args);
  }

  @Bean // Kafka Admin
  public NewTopic messagesTopic() {
    return new NewTopic("payments", 3, (short)1);
  }
}
