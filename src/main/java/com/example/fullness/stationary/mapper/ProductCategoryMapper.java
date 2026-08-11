package com.example.fullness.stationary.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.fullness.stationary.entity.ProductCategory;

@Mapper
public interface ProductCategoryMapper {
    public List<ProductCategory> selectAll();

    public List<ProductCategory> selectAllWithProducts();

    public ProductCategory selectById(int id);

    public ProductCategory selectByName(String name);

    public void insert(ProductCategory productCategory);
}
