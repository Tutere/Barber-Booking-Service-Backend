package com.Tuts.mapper;

import com.Tuts.model.BarberShop;
import com.Tuts.payload.BarberShopDTO;

public class BarberShopMapper {

    public static BarberShopDTO mapToDTO(BarberShop barberShop) {
        BarberShopDTO barberShopDTO = new BarberShopDTO();
        barberShopDTO.setId(barberShop.getId());
        barberShopDTO.setName(barberShop.getName());
        barberShopDTO.setAddress(barberShop.getAddress());
        barberShopDTO.setPhoneNumber(barberShop.getPhoneNumber());
        barberShopDTO.setEmail(barberShop.getEmail());
        barberShopDTO.setCity(barberShop.getCity());
        barberShopDTO.setOwnerId(barberShop.getOwnerId());
        barberShopDTO.setOpeningTime(barberShop.getOpeningTime());
        barberShopDTO.setClosingTime(barberShop.getClosingTime());
        barberShopDTO.setImages(barberShop.getImages());

        return barberShopDTO;
    }
}
