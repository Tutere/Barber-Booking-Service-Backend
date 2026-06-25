package com.Tuts.payload.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class BookingDTO {

    public Long id;

    private Long barberShopId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Set<Long> serviceOfferingIds;

    private Double totalPrice;

}
