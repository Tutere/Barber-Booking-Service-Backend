package com.Tuts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.Tuts.domain.BookingStatus;
import com.Tuts.model.Booking;
import com.Tuts.model.SalonReport;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.payload.dto.BookingRequest;
import com.Tuts.payload.dto.ServiceDTO;
import com.Tuts.payload.dto.UserDTO;

public interface BookingService {
    Booking createBooking(BookingRequest booking, UserDTO userDTO, BarberShopDTO barberShopDTO,
            Set<ServiceDTO> serviceDTOs);

    List<Booking> getBookingsByCustomerId(Long customerId);

    List<Booking> getBookingsByBarberShopId(Long barberShopId);

    Booking getBookingById(Long bookingId);

    Booking updateBookingStatus(Long bookingId, BookingStatus status);

    List<Booking> getBookingsByDate(LocalDate date, Long barberShopId);

    SalonReport getBarberShopReport(Long barberShopId);

}
