package com.Tuts.service;

import java.util.List;

import com.Tuts.model.BarberShop;
import com.Tuts.model.barberShop;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.payload.dto.UserDTO;

public interface BarberShopService {
    BarberShop createBarberShop(BarberShopDTO barberShop, UserDTO user); // user is the owner of the barber shop

    BarberShop updateBarberShop(BarberShopDTO barberShop, UserDTO user, Long barberShopId); // will check that owner is
                                                                                            // the same as the user who
                                                                                            // trying to update the
                                                                                            // barber shop

    List<BarberShop> getAllBarberShops();

    BarberShop getBarberShopById(Long barberShopId);

    BarberShop getBarberShopByOwnerId(Long ownerId);

    List<BarberShop> getBarberShopsByCity(String city);
}
