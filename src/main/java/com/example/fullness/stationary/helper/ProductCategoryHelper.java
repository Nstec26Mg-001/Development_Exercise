package com.example.fullness.stationary.helper;

import org.springframework.stereotype.Component;

import com.example.fullness.stationary.controller.form.ProductCategoryRegisterForm;
import com.example.fullness.stationary.entity.ProductCategory;

@Component
public class ProductCategoryHelper {

    public ProductCategory convertToEntity(ProductCategoryRegisterForm form) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(form.getName());
        return productCategory;
    }
}
