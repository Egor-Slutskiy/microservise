package com.example.data.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "payments")
public class PaymentEntity {
    @Id
    private long id;
    private long senderId;
    private long cardId;
    private long amount;
    private String comment;
}
