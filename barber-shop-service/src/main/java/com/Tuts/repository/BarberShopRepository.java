package com.Tuts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Tuts.model.BarberShop;
import java.util.List;

public interface BarberShopRepository extends JpaRepository<BarberShop, Long> {

    BarberShop findByOwnerId(Long id);

    @Query("select b from BarberShop b where" +
            " lower(b.city) like lower(concat('%', :keyword, '%')) or" +
            " lower(b.name) like lower(concat('%', :keyword, '%')) or" +
            " lower(b.address) like lower(concat('%', :keyword, '%'))")
    List<BarberShop> searchBarberShops(@Param("keyword") String keyword);

}
