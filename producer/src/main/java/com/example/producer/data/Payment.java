package com.example.producer.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment implements Payload {
  private long id;
  private long senderId;
  private long cardId;
  private long amount;
  private String comment;
}
