package com.Tuts.service;

import java.util.Set;

import com.Tuts.model.ServiceOffering;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.payload.dto.CategoryDTO;
import com.Tuts.payload.dto.ServiceDTO;

public interface ServiceOfferingService {

    ServiceOffering createService(BarberShopDTO barberShopDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);

    ServiceOffering updateService(Long serviceId, ServiceOffering service);

    Set<ServiceOffering> getAllServiceBySalonId(Long barbershopId, Long categoryId);

    Set<ServiceOffering> getServicesByIds(Set<Long> ids);
}
