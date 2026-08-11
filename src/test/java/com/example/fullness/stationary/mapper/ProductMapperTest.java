package com.example.fullness.stationary.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.factory.ProductMapperTestFactory;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "/sql/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
@Import(ProductMapperTestFactory.class)
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    private ProductMapperTestFactory productMapperTestFactory;

    public ProductMapperTest() {
        this.productMapperTestFactory = new ProductMapperTestFactory();
    }

    @Test
    @DisplayName("全件検索OKテスト")
    public void testSelectAll() {
        List<Product> expected = this.productMapperTestFactory.createProductList();

        List<Product> actual = this.productMapper.selectAll();

        assertInstanceOf(List.class, actual);
        expected.forEach(p -> assertInstanceOf(Product.class, p));
        assertEquals(5, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("全件検索（カテゴリ付き）OKテスト")
    public void testSelectAllWithCategory() {
        List<Product> expected = this.productMapperTestFactory.createProductListWithCategory();

        List<Product> actual = this.productMapper.selectAllWithCategory();

        assertInstanceOf(List.class, actual);
        expected.forEach(p -> assertInstanceOf(Product.class, p));
        assertEquals(5, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ID検索OKテスト")
    public void testSelectById() {
        Product expected = this.productMapperTestFactory.createProductEntityWithCategory(1);

        Product actual = this.productMapper.selectById(1);

        assertInstanceOf(Product.class, actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("登録OKテスト")
    public void testInsert() {
        Product newProduct =
                new Product(0, 1, "消しゴム", 100, "https://example.com/images/eraser.jpg", 0, null);

        this.productMapper.insert(newProduct);

        Product actual = this.productMapper.selectById(newProduct.getId());
        assertInstanceOf(Product.class, actual);
        assertEquals("消しゴム", actual.getName());
        assertEquals(100, actual.getPrice());
    }

    @Test
    @DisplayName("更新OKテスト")
    public void testUpdateById() {
        Product product = this.productMapper.selectById(1);
        product.setName("ボールペン(赤)");
        product.setPrice(180);

        this.productMapper.updateById(product);

        Product actual = this.productMapper.selectById(1);
        assertEquals("ボールペン(赤)", actual.getName());
        assertEquals(180, actual.getPrice());
    }

    @Test
    @DisplayName("削除OKテスト")
    public void testDeleteById() {
        // 外部キー制約のない商品を登録して削除テスト
        Product newProduct = new Product(0, 1, "テスト商品", 500, null, 0, null);
        this.productMapper.insert(newProduct);
        int newId = newProduct.getId();

        this.productMapper.deleteById(newId);

        Product actual = this.productMapper.selectById(newId);
        assertNull(actual);
    }

}
