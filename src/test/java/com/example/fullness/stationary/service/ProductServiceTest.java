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

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.mapper.ProductMapper;
import com.example.fullness.stationary.service.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("全件検索OKテスト")
    public void testFindAll() {
        List<Product> expected = List.of(
                new Product(1, 1, "ボールペン(黒)", 150, "https://example.com/images/pen.jpg", 0,
                        new ProductCategory(1, "文房具", null)),
                new Product(2, 1, "ノート(A5)", 200, "https://example.com/images/notebook.jpg", 0,
                        new ProductCategory(1, "文房具", null)));
        when(productMapper.selectAllWithCategory()).thenReturn(expected);

        List<Product> actual = productService.findAll();

        assertInstanceOf(List.class, actual);
        assertEquals(2, actual.size());
        assertEquals(expected, actual);
        verify(productMapper, times(1)).selectAllWithCategory();
    }

    @Test
    @DisplayName("カテゴリID検索OKテスト")
    public void testFindByCategoryId() {
        List<Product> expected = List.of(
                new Product(1, 1, "ボールペン(黒)", 150, "https://example.com/images/pen.jpg", 0,
                        new ProductCategory(1, "文房具", null)));
        when(productMapper.selectByCategoryId(1, 5, 0)).thenReturn(expected);

        List<Product> actual = productService.findByCategoryId(1, 1, 5);

        assertInstanceOf(List.class, actual);
        assertEquals(1, actual.size());
        assertEquals(expected, actual);
        verify(productMapper, times(1)).selectByCategoryId(1, 5, 0);
    }

    @Test
    @DisplayName("ページネーション検索OKテスト")
    public void testFindWithPagination() {
        List<Product> expected = List.of(
                new Product(1, 1, "ボールペン(黒)", 150, "https://example.com/images/pen.jpg", 0,
                        new ProductCategory(1, "文房具", null)));
        when(productMapper.selectWithPagination(5, 0)).thenReturn(expected);

        List<Product> actual = productService.findWithPagination(1, 5);

        assertInstanceOf(List.class, actual);
        assertEquals(1, actual.size());
        assertEquals(expected, actual);
        verify(productMapper, times(1)).selectWithPagination(5, 0);
    }

    @Test
    @DisplayName("ページネーション2ページ目OKテスト")
    public void testFindWithPaginationPage2() {
        List<Product> expected = List.of(
                new Product(6, 2, "USBケーブル", 500, "https://example.com/images/usb.jpg", 0,
                        new ProductCategory(2, "PC・周辺機器", null)));
        when(productMapper.selectWithPagination(5, 5)).thenReturn(expected);

        List<Product> actual = productService.findWithPagination(2, 5);

        assertEquals(expected, actual);
        verify(productMapper, times(1)).selectWithPagination(5, 5);
    }

    @Test
    @DisplayName("ID検索OKテスト")
    public void testFindById() {
        Product expected = new Product(1, 1, "ボールペン(黒)", 150, "https://example.com/images/pen.jpg", 0,
                new ProductCategory(1, "文房具", null));
        when(productMapper.selectById(1)).thenReturn(expected);

        Product actual = productService.findById(1);

        assertInstanceOf(Product.class, actual);
        assertEquals(expected, actual);
        verify(productMapper, times(1)).selectById(1);
    }

    @Test
    @DisplayName("全件カウントOKテスト")
    public void testCountAll() {
        when(productMapper.countAll()).thenReturn(5);

        int actual = productService.countAll();

        assertEquals(5, actual);
        verify(productMapper, times(1)).countAll();
    }

    @Test
    @DisplayName("カテゴリID件数カウントOKテスト")
    public void testCountByCategoryId() {
        when(productMapper.countByCategoryId(1)).thenReturn(2);

        int actual = productService.countByCategoryId(1);

        assertEquals(2, actual);
        verify(productMapper, times(1)).countByCategoryId(1);
    }
}
