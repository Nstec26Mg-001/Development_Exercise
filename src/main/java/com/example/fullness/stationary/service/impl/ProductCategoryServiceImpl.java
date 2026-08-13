package com.example.fullness.stationary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.controller.form.ProductCategoryRegisterForm;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.helper.ProductCategoryHelper;
import com.example.fullness.stationary.mapper.ProductCategoryMapper;
import com.example.fullness.stationary.service.ProductCategoryService;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductCategoryHelper productCategoryHelper;

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryMapper.selectAll();
    }

    @Override
    public ProductCategory findById(int id) {
        return productCategoryMapper.selectById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return productCategoryMapper.selectByName(name) != null;
    }

    @Override
    public void register(ProductCategoryRegisterForm form) {
        ProductCategory productCategory = productCategoryHelper.convertToEntity(form);
        productCategoryMapper.insert(productCategory);
    }
}
