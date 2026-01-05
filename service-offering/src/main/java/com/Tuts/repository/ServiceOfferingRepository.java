package com.Tuts.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tuts.model.ServiceOffering;

public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, Long> {
    Set<ServiceOffering> findByBarberShopId(Long barberShopId);
}
