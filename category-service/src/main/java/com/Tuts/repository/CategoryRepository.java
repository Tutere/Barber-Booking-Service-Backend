package com.Tuts.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tuts.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findByBarbershopId(Long barbershopId);
}
