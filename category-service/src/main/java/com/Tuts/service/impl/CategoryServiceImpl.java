package com.Tuts.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.Tuts.model.Category;
import com.Tuts.payload.BarberShopDTO;
import com.Tuts.repository.CategoryRepository;
import com.Tuts.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    // can either autowire here or just use requiredArgsConstructor as above for
    // entire class
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, BarberShopDTO barberShopDTO) {
        Category newCategory = new Category();

        newCategory.setName(category.getName());
        newCategory.setBarbershopId(barberShopDTO.getId());
        newCategory.setImage(category.getImage());

        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesByBarberShopId(Long barberShopId) {
        return categoryRepository.findByBarbershopId(barberShopId);
    }

    @Override
    public Category getCategoryById(Long categoryId) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            throw new Exception("Category not found with id: " + categoryId);
        }

        return category;
    }

    @Override
    public void deleteCategory(Long categoryId, Long barberShopId) throws Exception {
        Category category = getCategoryById(categoryId);

        if (category.getBarbershopId() != barberShopId) {
            throw new Exception("Category with id: " + categoryId + " does not belong to BarberShop with id: "
                    + barberShopId);
        }
        categoryRepository.deleteById(categoryId);
    }

}
