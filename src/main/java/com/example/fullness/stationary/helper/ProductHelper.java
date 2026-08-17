package com.example.fullness.stationary.helper;

import org.springframework.stereotype.Component;

import com.example.fullness.stationary.controller.form.ProductRegisterForm;
import com.example.fullness.stationary.entity.Product;

@Component
public class ProductHelper {

    public Product convertToEntity(ProductRegisterForm form) {
        Product product = new Product();
        product.setProductCategoryId(form.getCategoryId());
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setImageUrl(form.getImagePath());
        product.setDeleteFlag(0);
        return product;
    }
}
