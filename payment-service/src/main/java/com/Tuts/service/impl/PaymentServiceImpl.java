package com.Tuts.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Tuts.domain.PaymentMethod;
import com.Tuts.model.PaymentOrder;
import com.Tuts.payload.dto.BookingDTO;
import com.Tuts.payload.dto.UserDTO;
import com.Tuts.payload.response.PaymentLinkResponse;
import com.Tuts.repository.PaymentOrderRepository;
import com.Tuts.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentOrderRepository paymentOrderRepository;

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Override
    public PaymentLinkResponse createOrder(UserDTO userDto, BookingDTO bookingDto, PaymentMethod paymentMethod)
            throws StripeException {
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setAmount(bookingDto.getTotalPrice());
        paymentOrder.setPaymentMethod(paymentMethod);
        paymentOrder.setBarberShopId(bookingDto.getBarberShopId());
        paymentOrder.setBookingId(bookingDto.getId());
        paymentOrder.setUserId(bookingDto.getCustomerId());

        PaymentOrder savedOrder = paymentOrderRepository.save(paymentOrder);

        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();

        if (paymentMethod.equals(PaymentMethod.STRIPE)) {
            String paymentUrl = createStripePaymentLink(
                    userDto,
                    savedOrder.getAmount(),
                    savedOrder.getId());
            paymentLinkResponse.setPaymentLinkUrl(paymentUrl);
        }
        return paymentLinkResponse;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        return paymentOrderRepository.findById(id).orElseThrow(
                () -> new Exception("Payment order was not found!"));
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return paymentOrderRepository.findByPaymentLinkId(paymentId);
    }

    @Override
    public String createStripePaymentLink(UserDTO userDto, Double amount, Long orderId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-success/" + orderId)
                .setCancelUrl("http://localhost:3000/payment-cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount((long) Math.ceil(amount * 100))
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Barber Appointment Booking")
                                        .build())
                                .build())
                        .build())
                .build();

        Session session = Session.create(params);
        return session.getUrl();
    }

}
