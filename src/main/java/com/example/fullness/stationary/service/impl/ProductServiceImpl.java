package com.example.fullness.stationary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.controller.form.ProductRegisterForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.helper.ProductHelper;
import com.example.fullness.stationary.mapper.ProductMapper;
import com.example.fullness.stationary.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductHelper productHelper;

    @Override
    public List<Product> findAll() {
        return productMapper.selectAllWithCategory();
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return productMapper.selectByCategoryId(categoryId, pageSize, offset);
    }

    @Override
    public List<Product> findWithPagination(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return productMapper.selectWithPagination(pageSize, offset);
    }

    @Override
    public Product findById(int id) {
        return productMapper.selectById(id);
    }

    @Override
    public int countAll() {
        return productMapper.countAll();
    }

    @Override
    public int countByCategoryId(int categoryId) {
        return productMapper.countByCategoryId(categoryId);
    }

    @Override
    public void register(ProductRegisterForm form) {
        Product product = productHelper.convertToEntity(form);
        productMapper.insert(product);
    }
}
