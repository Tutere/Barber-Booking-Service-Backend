package com.Tuts.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.Tuts.model.ServiceOffering;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.payload.dto.CategoryDTO;
import com.Tuts.payload.dto.ServiceDTO;
import com.Tuts.repository.ServiceOfferingRepository;
import com.Tuts.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(BarberShopDTO barberShopDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO) {
        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setImage(serviceDTO.getImage());
        serviceOffering.setBarberShopId(barberShopDTO.getId());
        serviceOffering.setName(serviceDTO.getName());
        serviceOffering.setDescription(serviceDTO.getDescription());
        serviceOffering.setCategoryId(categoryDTO.getId());
        serviceOffering.setPrice(serviceDTO.getPrice());
        serviceOffering.setDuration(serviceDTO.getDuration());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {
        ServiceOffering existingService = serviceOfferingRepository.findById(serviceId)
                .orElse(null);

        if (existingService == null) {
            throw new Exception("Service not found with id: " + serviceId);
        }

        existingService.setImage(service.getImage());
        existingService.setName(service.getName());
        existingService.setDescription(service.getDescription());
        existingService.setPrice(service.getPrice());
        existingService.setDuration(service.getDuration());

        return serviceOfferingRepository.save(existingService);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long barberShopId, Long categoryId) {
        Set<ServiceOffering> services = serviceOfferingRepository.findByBarberShopId(barberShopId);
        if (categoryId != null) {
            services.removeIf(service -> !service.getCategoryId().equals(categoryId));
        }
        return services;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
        List<ServiceOffering> services = serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(services);
    }

    @Override
    public ServiceOffering getServiceById(Long serviceId) throws Exception {
        ServiceOffering serviceOffering = serviceOfferingRepository.findById(serviceId)
                .orElse(null);

        if (serviceOffering == null) {
            throw new Exception("Service not found with id: " + serviceId);
        }

        return serviceOffering;
    }

}
