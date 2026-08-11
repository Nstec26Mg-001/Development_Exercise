package com.example.fullness.stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;

    private int productCategoryId;

    private String name;

    private int price;

    private String imageUrl;

    private int deleteFlag;

    private ProductCategory productCategory;
}
