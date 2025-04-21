package com.Tuts.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Tuts.model.BarberShop;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.payload.dto.UserDTO;
import com.Tuts.repository.BarberShopRepository;
import com.Tuts.service.BarberShopService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BarberShopServiceImpl implements BarberShopService {

    private final BarberShopRepository barberShopRepository;

    @Override
    public BarberShop createBarberShop(BarberShopDTO req, UserDTO user) {
        BarberShop barberShop = new BarberShop();
        barberShop.setName(req.getName());
        barberShop.setAddress(req.getAddress());
        barberShop.setEmail(req.getEmail());
        barberShop.setCity(req.getCity());
        barberShop.setImages(req.getImages());
        barberShop.setPhoneNumber(req.getPhoneNumber());
        barberShop.setOwnerId(user.getId());
        barberShop.setOpeningTime(req.getOpeningTime());
        barberShop.setClosingTime(req.getClosingTime());

        return barberShopRepository.save(barberShop);
    }

    @Override
    public BarberShop updateBarberShop(BarberShopDTO barberShop, UserDTO user, Long barberShopId) throws Exception {
        BarberShop existingBarberShop = barberShopRepository.findById(barberShopId)
                .orElse(null);

        if (existingBarberShop != null && existingBarberShop.getOwnerId().equals(user.getId())) {
            existingBarberShop.setName(barberShop.getName());
            existingBarberShop.setAddress(barberShop.getAddress());
            existingBarberShop.setEmail(barberShop.getEmail());
            existingBarberShop.setCity(barberShop.getCity());
            existingBarberShop.setImages(barberShop.getImages());
            existingBarberShop.setPhoneNumber(barberShop.getPhoneNumber());
            existingBarberShop.setOpeningTime(barberShop.getOpeningTime());
            existingBarberShop.setClosingTime(barberShop.getClosingTime());

            return barberShopRepository.save(existingBarberShop);
        }
        throw new Exception("Barbershop not found");
    }

    @Override
    public List<BarberShop> getAllBarberShops() {
        return barberShopRepository.findAll();
    }

    @Override
    public BarberShop getBarberShopById(Long barberShopId) throws Exception {
        BarberShop barberShop = barberShopRepository.findById(barberShopId)
                .orElse(null);
        if (barberShop == null) {
            throw new Exception("Barbershop not found");
        }
        return barberShop;
    }

    @Override
    public BarberShop getBarberShopByOwnerId(Long ownerId) {
        return barberShopRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<BarberShop> searchBarberShopsByCity(String city) {
        return barberShopRepository.searchBarberShops(city);
    }

}
