package com.Tuts.service.impl;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.stereotype.Service;

import com.Tuts.payload.BarberShopDTO;
import com.Tuts.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public Category saveCategory(Category category, BarberShopDTO barberShopDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCategory'");
    }

    @Override
    public List<Category> getAllCategoriesByBarberShopId(Long barberShopId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCategoriesByBarberShopId'");
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryById'");
    }

    @Override
    public void deleteCategory(Long categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
    }

}
