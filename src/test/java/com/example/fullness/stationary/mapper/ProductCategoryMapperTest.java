package com.example.fullness.stationary.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.factory.ProductCategoryMapperTestFactory;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "/sql/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
@Import(ProductCategoryMapperTestFactory.class)
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    private ProductCategoryMapperTestFactory productCategoryMapperTestFactory;

    public ProductCategoryMapperTest() {
        this.productCategoryMapperTestFactory = new ProductCategoryMapperTestFactory();
    }

    @Test
    @DisplayName("全件検索OKテスト")
    public void testSelectAll() {
        List<ProductCategory> expected =
                this.productCategoryMapperTestFactory.createProductCategoryListWithoutProducts();

        List<ProductCategory> actual = this.productCategoryMapper.selectAll();

        assertInstanceOf(List.class, actual);
        expected.forEach(pc -> assertInstanceOf(ProductCategory.class, pc));
        assertEquals(3, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("全件検索（商品付き）OKテスト")
    public void testSelectAllWithProducts() {
        List<ProductCategory> expected =
                this.productCategoryMapperTestFactory.createProductCategoryList();

        List<ProductCategory> actual = this.productCategoryMapper.selectAllWithProducts();

        assertInstanceOf(List.class, actual);
        expected.forEach(pc -> assertInstanceOf(ProductCategory.class, pc));
        assertEquals(3, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ID検索OKテスト")
    public void testSelectById() {
        ProductCategory expected =
                this.productCategoryMapperTestFactory.createProductCategoryEntity(1);

        ProductCategory actual = this.productCategoryMapper.selectById(1);

        assertInstanceOf(ProductCategory.class, actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("カテゴリ名検索OKテスト")
    public void testSelectByName() {
        ProductCategory expected =
                this.productCategoryMapperTestFactory.createProductCategoryEntity(2);

        ProductCategory actual = this.productCategoryMapper.selectByName("PC・周辺機器");

        assertInstanceOf(ProductCategory.class, actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("登録OKテスト")
    public void testInsert() {
        ProductCategory newCategory = new ProductCategory(0, "オフィス用品", null);

        this.productCategoryMapper.insert(newCategory);

        ProductCategory actual = this.productCategoryMapper.selectByName("オフィス用品");
        assertInstanceOf(ProductCategory.class, actual);
        assertEquals("オフィス用品", actual.getName());
    }

}
