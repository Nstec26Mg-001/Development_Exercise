package com.example.fullness.stationary.factory;

import java.util.List;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;

public class ProductCategoryMapperTestFactory {

    private List<ProductCategory> productCategories;

    public ProductCategoryMapperTestFactory() {
        this.productCategories = List.of(
                new ProductCategory(1, "文房具",
                        List.of(new Product(1, 1, "ボールペン(黒)", 150,
                                "https://example.com/images/pen.jpg", 0, null),
                                new Product(2, 1, "ノート(A5)", 200,
                                        "https://example.com/images/notebook.jpg", 0, null))),
                new ProductCategory(2, "PC・周辺機器",
                        List.of(new Product(3, 2, "ワイヤレスマウス", 2980,
                                "https://example.com/images/mouse.jpg", 0, null),
                                new Product(4, 2, "メカニカルキーボード", 12800,
                                        "https://example.com/images/keyboard.jpg", 0, null))),
                new ProductCategory(3, "家具", List.of(new Product(5, 3, "オフィスチェア", 19800,
                        "https://example.com/images/chair.jpg", 0, null))));
    }

    public List<ProductCategory> createProductCategoryList() {
        return this.productCategories;
    }

    public ProductCategory createProductCategoryEntity(int id) {
        return this.productCategories.stream().filter(pc -> pc.getId() == id).toList().get(0);
    }

    public List<ProductCategory> createProductCategoryListWithoutProducts() {
        return List.of(new ProductCategory(1, "文房具", null), new ProductCategory(2, "PC・周辺機器", null),
                new ProductCategory(3, "家具", null));
    }
}
