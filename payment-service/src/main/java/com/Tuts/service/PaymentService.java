package com.Tuts.service;

import com.Tuts.domain.PaymentMethod;
import com.Tuts.model.PaymentOrder;
import com.Tuts.payload.dto.BookingDTO;
import com.Tuts.payload.dto.UserDTO;
import com.Tuts.payload.response.PaymentLinkResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
    PaymentLinkResponse createOrder(UserDTO userDto, BookingDTO bookingDto, PaymentMethod paymentMethod)
            throws StripeException;

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    String createStripePaymentLink(UserDTO userDto,
            Double amount,
            Long orderId) throws StripeException;

}
