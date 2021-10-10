package com.example.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDto {
  private long id;
  private long amount;
  private long card_id;
  private String comment;
  private long sender_id;
}
