package com.github.budwing.obo.payment.repository;

import com.github.budwing.obo.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
