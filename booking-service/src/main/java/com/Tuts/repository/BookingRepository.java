package com.Tuts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tuts.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findByBarberShopId(Long barberShopId);
}