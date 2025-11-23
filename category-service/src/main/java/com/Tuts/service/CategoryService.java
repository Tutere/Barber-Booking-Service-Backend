package com.Tuts.service;

import java.util.List;
import java.util.Locale.Category;

import com.Tuts.payload.BarberShopDTO;

public interface CategoryService {

    Category saveCategory(Category category, BarberShopDTO barberShopDTO);

    List<Category> getAllCategoriesByBarberShopId(Long barberShopId);

    Category getCategoryById(Long categoryId);

    void deleteCategory(Long categoryId);
}
