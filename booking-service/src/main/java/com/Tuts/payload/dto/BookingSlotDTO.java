package com.Tuts.payload.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingSlotDTO {
    private LocalDateTime starTime;
    private LocalDateTime endTime;
}
