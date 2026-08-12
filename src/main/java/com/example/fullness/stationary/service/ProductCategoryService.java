package com.example.fullness.stationary.service;

import java.util.List;

import com.example.fullness.stationary.entity.ProductCategory;

public interface ProductCategoryService {

    List<ProductCategory> findAll();

    ProductCategory findById(int id);
}
