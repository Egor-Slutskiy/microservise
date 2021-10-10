package com.example.data.repository;

import com.example.data.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRepository extends JpaRepository<PaymentEntity,Long> {
    List<PaymentEntity> findBySenderId(long senderId);
}
