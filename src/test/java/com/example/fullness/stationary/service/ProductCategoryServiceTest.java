package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.mapper.ProductCategoryMapper;
import com.example.fullness.stationary.service.impl.ProductCategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceTest {

    @Mock
    private ProductCategoryMapper productCategoryMapper;

    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    @DisplayName("全件検索OKテスト")
    public void testFindAll() {
        List<ProductCategory> expected = List.of(
                new ProductCategory(1, "文房具", null),
                new ProductCategory(2, "PC・周辺機器", null),
                new ProductCategory(3, "家具", null));
        when(productCategoryMapper.selectAll()).thenReturn(expected);

        List<ProductCategory> actual = productCategoryService.findAll();

        assertInstanceOf(List.class, actual);
        assertEquals(3, actual.size());
        assertEquals(expected, actual);
        verify(productCategoryMapper, times(1)).selectAll();
    }

    @Test
    @DisplayName("ID検索OKテスト")
    public void testFindById() {
        ProductCategory expected = new ProductCategory(1, "文房具", null);
        when(productCategoryMapper.selectById(1)).thenReturn(expected);

        ProductCategory actual = productCategoryService.findById(1);

        assertInstanceOf(ProductCategory.class, actual);
        assertEquals(expected, actual);
        verify(productCategoryMapper, times(1)).selectById(1);
    }
}
