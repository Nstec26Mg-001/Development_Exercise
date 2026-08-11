package com.example.fullness.stationary.factory;

import java.util.List;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;

public class ProductMapperTestFactory {

    private List<Product> products;
    private List<Product> productsWithCategory;

    public ProductMapperTestFactory() {
        this.products = List.of(
                new Product(1, 1, "ボールペン(黒)", 150, "https://example.com/images/pen.jpg", 0, null),
                new Product(2, 1, "ノート(A5)", 200, "https://example.com/images/notebook.jpg", 0, null),
                new Product(3, 2, "ワイヤレスマウス", 2980, "https://example.com/images/mouse.jpg", 0, null),
                new Product(4, 2, "メカニカルキーボード", 12800, "https://example.com/images/keyboard.jpg", 0, null),
                new Product(5, 3, "オフィスチェア", 19800, "https://example.com/images/chair.jpg", 0, null));

        this.productsWithCategory = List.of(
                new Product(1, 1, "ボールペン(黒)", 150, "https://example.com/images/pen.jpg", 0,
                        new ProductCategory(1, "文房具", null)),
                new Product(2, 1, "ノート(A5)", 200, "https://example.com/images/notebook.jpg", 0,
                        new ProductCategory(1, "文房具", null)),
                new Product(3, 2, "ワイヤレスマウス", 2980, "https://example.com/images/mouse.jpg", 0,
                        new ProductCategory(2, "PC・周辺機器", null)),
                new Product(4, 2, "メカニカルキーボード", 12800, "https://example.com/images/keyboard.jpg", 0,
                        new ProductCategory(2, "PC・周辺機器", null)),
                new Product(5, 3, "オフィスチェア", 19800, "https://example.com/images/chair.jpg", 0,
                        new ProductCategory(3, "家具", null)));
    }

    public List<Product> createProductList() {
        return this.products;
    }

    public List<Product> createProductListWithCategory() {
        return this.productsWithCategory;
    }

    public Product createProductEntity(int id) {
        return this.products.stream().filter(p -> p.getId() == id).toList().get(0);
    }

    public Product createProductEntityWithCategory(int id) {
        return this.productsWithCategory.stream().filter(p -> p.getId() == id).toList().get(0);
    }
}
