package com.Tuts.payload;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
import lombok.Data;

@Data // Lombok will generate getters, setters, toString, equals, hashCode methods
      // constructors
public class BarberShopDTO {

    private Long id;

    private String name;

    @ElementCollection
    private List<String> images;

    private String address;

    private String phoneNumber;

    private String email;

    private String city;

    private Long ownerId;

    private LocalTime openingTime;

    private LocalTime closingTime;
}
