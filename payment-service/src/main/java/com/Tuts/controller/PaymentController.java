package com.Tuts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Tuts.model.PaymentOrder;
import com.Tuts.payload.dto.BookingDTO;
import com.Tuts.payload.dto.UserDTO;
import com.Tuts.payload.response.PaymentLinkResponse;
import com.Tuts.service.PaymentService;
import com.stripe.exception.StripeException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDTO booking,
            @RequestParam com.Tuts.domain.PaymentMethod paymentMethod) throws StripeException {

        UserDTO userDto = new UserDTO();
        userDto.setFullname("Tuts");
        userDto.setEmail("tuteredurie@hotmail.com");
        userDto.setId(1L);

        PaymentLinkResponse paymentLinkResponse = paymentService.createOrder(
                userDto,
                booking,
                paymentMethod);

        return new ResponseEntity<>(paymentLinkResponse, HttpStatus.OK);
    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(
            @PathVariable("paymentOrderId") Long paymentOrderId) throws Exception {
        PaymentOrder order = paymentService.getPaymentOrderById(paymentOrderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PatchMapping("/proceed")
    public ResponseEntity<PaymentOrder> proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId) {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);
        paymentService.proceedPaymentStatus(paymentOrder, paymentId, paymentLinkId);
        return new ResponseEntity<>(paymentOrder, HttpStatus.OK);
    }
}
