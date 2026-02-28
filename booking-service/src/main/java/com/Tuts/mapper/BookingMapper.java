package com.Tuts.mapper;

import com.Tuts.model.Booking;
import com.Tuts.payload.dto.BookingDTO;

public class BookingMapper {

    public static BookingDTO toDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setBarberShopId(booking.getBarberShopId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setServiceOfferingIds(booking.getServiceOfferingIds());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setTotalPrice(booking.getTotalPrice());
        return bookingDTO;
    }
}
