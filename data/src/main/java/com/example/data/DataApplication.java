package com.example.data;

import com.example.data.data.Payment;
import com.example.data.dto.ResponseDto;
import com.example.data.entity.PaymentEntity;
import com.example.data.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class DataApplication {
  private final Log logger = LogFactory.getLog(this.getClass());
  private final DataRepository repository;
  @Setter(onMethod_={@Value("${app.id}")})
  private String id;

  @GetMapping
  public List<ResponseDto> endpoint(@RequestHeader("Authn") long id) {
    final var payments = repository.findBySenderId(id);
    return getResponseDtos(payments);
  }

  @GetMapping("/admin")
  public List<ResponseDto> getAll(){
    final var payments = repository.findAll();
    return getResponseDtos(payments);
  }

  private List<ResponseDto> getResponseDtos(List<PaymentEntity> payments) {
    List<ResponseDto> paymentsList= new ArrayList<>();
    if(!payments.isEmpty()){
      for(PaymentEntity payment:payments){
        paymentsList.add(new ResponseDto(
                payment.getId(),
                payment.getAmount(),
                payment.getCardId(),
                payment.getComment(),
                payment.getSenderId()
        ));
      }
    }
    return paymentsList;
  }

  public static void main(String[] args) {
    SpringApplication.run(DataApplication.class, args);
  }

  @KafkaListener(groupId = "data", topics = "consumed")
  public void listen(Payment message, ConsumerRecord<String, Payment> record, Acknowledgment acknowledgment){
    repository.save(new PaymentEntity(
            message.getId(),
            message.getSenderId(),
            message.getCardId(),
            message.getAmount(),
            message.getComment()
    ));
    logger.info(message);
    acknowledgment.acknowledge();
  }
}
