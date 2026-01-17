package com.Tuts.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.Tuts.domain.BookingStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    private Long barberShopId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ElementCollection
    private Set<Long> serviceOfferingIds;

    private BookingStatus status = BookingStatus.PENDING;

    private int totalPrice;
}
