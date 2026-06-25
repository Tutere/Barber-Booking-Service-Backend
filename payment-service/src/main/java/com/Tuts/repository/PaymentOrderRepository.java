package com.Tuts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tuts.model.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder findByPaymentLinkId(String paymentLinkId);
}
