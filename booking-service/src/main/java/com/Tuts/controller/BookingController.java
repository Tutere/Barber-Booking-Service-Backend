package com.Tuts.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Tuts.domain.BookingStatus;
import com.Tuts.mapper.BookingMapper;
import com.Tuts.model.Booking;
import com.Tuts.model.SalonReport;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.payload.dto.BookingDTO;
import com.Tuts.payload.dto.BookingRequest;
import com.Tuts.payload.dto.BookingSlotDTO;
import com.Tuts.payload.dto.ServiceDTO;
import com.Tuts.payload.dto.UserDTO;
import com.Tuts.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam Long barberShopId,
            @RequestBody BookingRequest bookingRequest) throws Exception {

        UserDTO user = new UserDTO();
        user.setId(1L); // Dummy user ID for illustration

        BarberShopDTO barberShop = new BarberShopDTO();
        barberShop.setId(barberShopId);
        barberShop.setOpeningTime(LocalTime.of(9, 0)); // shop opens at 9 AM
        barberShop.setClosingTime(LocalTime.of(18, 0)); // closes at 6 PM

        Set<ServiceDTO> serviceDTOSet = new HashSet<>();

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L); // Dummy service ID for illustration
        serviceDTO.setPrice(399);
        serviceDTO.setDuration(45);
        serviceDTO.setDescription("Haircut for men");

        serviceDTOSet.add(serviceDTO);

        Booking booking = bookingService.createBooking(bookingRequest, user, barberShop, serviceDTOSet);

        return ResponseEntity.ok(booking);

    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingsByCustomer() {

        List<Booking> bookings = bookingService.getBookingsByCustomerId(1L);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/barbershop")
    public ResponseEntity<Set<BookingDTO>> getBookingsByBarberShop() {

        List<Booking> bookings = bookingService.getBookingsByBarberShopId(1L);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingsById(@PathVariable Long bookingId) {

        Booking booking = bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long bookingId,
            @RequestParam BookingStatus status) throws Exception {
        Booking booking = bookingService.updateBookingStatus(bookingId, status);

        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @GetMapping("/slots/barbershop/{barberShopId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookedSlots(@PathVariable Long barberShopId,
            @RequestParam(required = false) LocalDate date) {

        List<Booking> bookings = bookingService.getBookingsByDate(date, barberShopId);

        List<BookingSlotDTO> bookingSlots = bookings.stream().map(booking -> {
            BookingSlotDTO slotDTO = new BookingSlotDTO();
            slotDTO.setStarTime(booking.getStartTime());
            slotDTO.setEndTime(booking.getEndTime());
            return slotDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(bookingSlots);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getBarberShopReport() {

        SalonReport report = bookingService.getBarberShopReport(1L);

        return ResponseEntity.ok(report);
    }

    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings) {
        return bookings.stream().map(booking -> {
            return BookingMapper.toDTO(booking);
        }).collect(Collectors.toSet());
    }

}
