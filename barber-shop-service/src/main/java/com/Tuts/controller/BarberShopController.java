package com.Tuts.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Tuts.mapper.BarberShopMapper;
import com.Tuts.model.BarberShop;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.payload.dto.UserDTO;
import com.Tuts.service.BarberShopService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/barbershops")
@RequiredArgsConstructor
public class BarberShopController {

    private final BarberShopService barberShopService;

    @PostMapping // will use base endpoint above
    public ResponseEntity<BarberShopDTO> createBarberShop(@RequestBody BarberShopDTO barberShopDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L); // temp until authentication is implemented. Will use logged in user id
        BarberShop barberShop = barberShopService.createBarberShop(barberShopDTO, userDTO);
        BarberShopDTO barberShopDTO2 = BarberShopMapper.mapToDTO(barberShop);

        return ResponseEntity.ok(barberShopDTO2);
    }

    @PatchMapping("/{barberShopId}")
    public ResponseEntity<BarberShopDTO> updateBarberShop(@RequestBody BarberShopDTO barberShopDTO,
            @PathVariable Long barberShopId) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L); // temp until authentication is implemented
        BarberShop barberShop = barberShopService.updateBarberShop(barberShopDTO, userDTO, barberShopId);
        BarberShopDTO barberShopDTO2 = BarberShopMapper.mapToDTO(barberShop);

        return ResponseEntity.ok(barberShopDTO2);
    }

    @GetMapping()
    public ResponseEntity<List<BarberShopDTO>> getBarberShops() throws Exception {
        List<BarberShop> barberShops = barberShopService.getAllBarberShops();

        List<BarberShopDTO> barberShopDTOs = barberShops.stream()
                .map(barberShop -> {
                    BarberShopDTO barberShopDTO = BarberShopMapper.mapToDTO(barberShop);
                    return barberShopDTO;
                }).toList();
        return ResponseEntity.ok(barberShopDTOs);
    }

    @GetMapping("/{barberShopId}")
    public ResponseEntity<BarberShopDTO> getBarberShopById(@PathVariable Long barberShopId) throws Exception {

        BarberShop barberShop = barberShopService.getBarberShopById(barberShopId);

        BarberShopDTO barberShopDTO = BarberShopMapper.mapToDTO(barberShop);
        return ResponseEntity.ok(barberShopDTO);

    }

    @GetMapping("/search")
    public ResponseEntity<List<BarberShopDTO>> searchBarberShops(@RequestParam("city") String city) throws Exception {
        List<BarberShop> barberShops = barberShopService.searchBarberShopsByCity(city);

        List<BarberShopDTO> barberShopDTOs = barberShops.stream()
                .map(barberShop -> {
                    BarberShopDTO barberShopDTO = BarberShopMapper.mapToDTO(barberShop);
                    return barberShopDTO;
                }).toList();
        return ResponseEntity.ok(barberShopDTOs);
    }

    @GetMapping("/owner")
    public ResponseEntity<BarberShopDTO> getBarberShopByOwnerId() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L); // temp until authentication is implemented

        BarberShop barberShop = barberShopService.getBarberShopByOwnerId(userDTO.getId());

        BarberShopDTO barberShopDTO = BarberShopMapper.mapToDTO(barberShop);
        return ResponseEntity.ok(barberShopDTO);

    }

}
