package com.example.fullness.stationary.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.fullness.stationary.entity.Product;

@Mapper
public interface ProductMapper {
    public List<Product> selectAll();

    public List<Product> selectAllWithCategory();

    public List<Product> selectByCategoryId(@Param("categoryId") int categoryId,
            @Param("limit") int limit, @Param("offset") int offset);

    public List<Product> selectWithPagination(@Param("limit") int limit,
            @Param("offset") int offset);

    public int countAll();

    public int countByCategoryId(@Param("categoryId") int categoryId);

    public Product selectById(int id);

    public void insert(Product product);

    public void updateById(Product product);

    public void deleteById(int id);
}
