package com.Tuts.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Tuts.domain.BookingStatus;
import com.Tuts.model.Booking;
import com.Tuts.model.SalonReport;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.payload.dto.BookingRequest;
import com.Tuts.payload.dto.ServiceDTO;
import com.Tuts.payload.dto.UserDTO;
import com.Tuts.repository.BookingRepository;
import com.Tuts.service.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking, UserDTO userDTO, BarberShopDTO barberShopDTO,
            Set<ServiceDTO> serviceDTOs) throws Exception {

        int totalDuration = serviceDTOs.stream().mapToInt(ServiceDTO::getDuration).sum();
        LocalDateTime bookingStarTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStarTime.plusMinutes(totalDuration);

        Boolean isTimeSlotAvailable = isTimeSlotAvailable(barberShopDTO, bookingStarTime, bookingEndTime);

        if (!isTimeSlotAvailable) {
            throw new Exception("Time slot not available, please choose a different time");
        }

        int totalPrice = serviceDTOs.stream().mapToInt(ServiceDTO::getPrice).sum();

        Set<Long> serviceOfferingIds = serviceDTOs.stream().map(ServiceDTO::getId).collect(Collectors.toSet());

        Booking createdBooking = new Booking();
        createdBooking.setCustomerId(userDTO.getId());
        createdBooking.setBarberShopId(barberShopDTO.getId());
        createdBooking.setServiceOfferingIds(serviceOfferingIds);
        createdBooking.setStartTime(bookingStarTime);
        createdBooking.setEndTime(bookingEndTime);
        createdBooking.setTotalPrice(totalPrice);
        createdBooking.setStatus(BookingStatus.PENDING);

        return bookingRepository.save(createdBooking);
    }

    public Boolean isTimeSlotAvailable(BarberShopDTO barberShopDTO, LocalDateTime bookingStartTime,
            LocalDateTime bookingEndTime) throws Exception {

        LocalDateTime barbershopOpenTime = barberShopDTO.getOpeningTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime barbershopCloseTime = barberShopDTO.getClosingTime().atDate(bookingStartTime.toLocalDate());

        if (bookingStartTime.isBefore(barbershopOpenTime)
                || bookingEndTime.isAfter(barbershopCloseTime)) {
            throw new Exception("Booking time is outside of barber shop operating hours");
        }

        List<Booking> existingBookings = getBookingsByBarberShopId(barberShopDTO.getId());
        for (Booking existingBooking : existingBookings) {
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();
            if (bookingStartTime.isBefore(existingBookingEndTime)
                    && bookingEndTime.isAfter(existingBookingStartTime)) {
                return false;
            }

            if (existingBookingStartTime.isEqual(bookingStartTime)
                    || existingBookingEndTime.isEqual(bookingEndTime)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Booking> getBookingsByCustomerId(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsByBarberShopId(Long barberShopId) {
        return bookingRepository.findByBarberShopId(barberShopId);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
    }

    @Override
    public Booking updateBookingStatus(Long bookingId, BookingStatus status) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long barberShopId) {
        List<Booking> allBookings = getBookingsByBarberShopId(barberShopId);
        if (date == null) {
            return allBookings;
        }
        return allBookings.stream()
                .filter(booking -> booking.getStartTime().toLocalDate().isEqual(date))
                .collect(Collectors.toList());
    }

    @Override
    public SalonReport getBarberShopReport(Long barberShopId) {
        List<Booking> bookings = getBookingsByBarberShopId(barberShopId);
        double totalEarnings = bookings.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();

        Integer totalBookings = bookings.size();

        List<Booking> cancelledBookings = bookings.stream()
                .filter(booking -> booking.getStatus() == BookingStatus.CANCELLED)
                .collect(Collectors.toList());

        Double totalRefund = cancelledBookings.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();

        SalonReport report = new SalonReport();
        report.setSalonId(barberShopId);
        report.setTotalRefund(totalRefund);
        report.setTotalEarnings(totalEarnings);
        report.setTotalBookings(totalBookings);
        report.setTotalCancelledBookings(cancelledBookings.size());

        return report;
    }

}
