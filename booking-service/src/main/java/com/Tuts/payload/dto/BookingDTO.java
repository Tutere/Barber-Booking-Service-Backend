package com.Tuts.payload.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.Tuts.domain.BookingStatus;

import lombok.Data;

@Data
public class BookingDTO {

    public Long id;

    private Long barberShopId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Set<Long> serviceOfferingIds;

    private BookingStatus status = BookingStatus.PENDING;

    private int totalPrice;

}
