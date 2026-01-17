package com.Tuts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Tuts.model.Booking;
import com.Tuts.payload.dto.BookingRequest;
import com.Tuts.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    public ResponseEntity<Booking> createBooking(@RequestParam Long barberShopId,
            @RequestBody BookingRequest bookingRequest) {
        // Implementation here
        return ResponseEntity.ok().build();
    }

}
