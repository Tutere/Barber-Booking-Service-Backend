package com.Tuts.service;

import java.util.List;
import java.util.Set;

import com.Tuts.model.Category;
import com.Tuts.payload.BarberShopDTO;

public interface CategoryService {

    Category saveCategory(Category category, BarberShopDTO barberShopDTO);

    Set<Category> getAllCategoriesByBarberShopId(Long barberShopId);

    Category getCategoryById(Long categoryId) throws Exception;

    void deleteCategory(Long categoryId, Long barberShopId) throws Exception;
}
