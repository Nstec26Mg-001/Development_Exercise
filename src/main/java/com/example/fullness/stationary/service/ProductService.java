package com.example.fullness.stationary.service;

import java.util.List;

import com.example.fullness.stationary.controller.form.ProductRegisterForm;
import com.example.fullness.stationary.entity.Product;

public interface ProductService {

    List<Product> findAll();

    List<Product> findByCategoryId(int categoryId, int page, int pageSize);

    List<Product> findWithPagination(int page, int pageSize);

    Product findById(int id);

    int countAll();

    int countByCategoryId(int categoryId);

    void register(ProductRegisterForm form);
}
