package com.Tuts.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tuts.model.ServiceOffering;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.payload.dto.CategoryDTO;
import com.Tuts.payload.dto.ServiceDTO;
import com.Tuts.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/service-offering/barbershop-owner")
public class BarberShopServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDTO serviceDTO) {
        BarberShopDTO barberShopDTO = new BarberShopDTO();
        barberShopDTO.setId(1L); // Hardcoded barber shop ID until jwt implementation

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategory());

        ServiceOffering serviceOffering = serviceOfferingService.createService(barberShopDTO, serviceDTO, categoryDTO);

        return ResponseEntity.ok(serviceOffering);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable Long id,
            @RequestBody ServiceOffering serviceOffering) throws Exception {

        ServiceOffering updatedServiceOffering = serviceOfferingService.updateService(id, serviceOffering);
        return ResponseEntity.ok(updatedServiceOffering);
    }

}
