package com.example.fullness.stationary.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.fullness.stationary.entity.Product;

@Mapper
public interface ProductMapper {
    public List<Product> selectAll();

    public List<Product> selectAllWithCategory();

    public Product selectById(int id);

    public void insert(Product product);

    public void updateById(Product product);

    public void deleteById(int id);
}
