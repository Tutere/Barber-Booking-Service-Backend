package com.Tuts.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tuts.model.Category;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories/barbershop-owner")
public class BarbershopCategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {

        BarberShopDTO barberShopDTO = new BarberShopDTO();
        barberShopDTO.setId(1L); // Replace with actual barbershop owner ID retrieval logic - just using as test
                                 // for now
        Category createdCategory = categoryService.saveCategory(category, barberShopDTO);
        return ResponseEntity.ok(createdCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws Exception {

        BarberShopDTO barberShopDTO = new BarberShopDTO();
        barberShopDTO.setId(1L); // Replace with actual barbershop owner ID retrieval logic - just using as test
                                 // for now
        categoryService.deleteCategory(id, barberShopDTO.getId());
        return ResponseEntity.ok("Category deleted successfully");
    }

}
