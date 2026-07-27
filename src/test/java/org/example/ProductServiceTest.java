package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService productService;
    private ArrayList<Product> products;

    @BeforeEach
    void setUp() {
        productService = new ProductService();

        products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 899.99, 10));
        products.add(new Product(2, "Mouse", 24.99, 30));
        products.add(new Product(3, "Keyboard", 49.99, 20));
    }

    @Test
    void findProductByIdShouldReturnProduct() {
        Product result =
                productService.findProductById(products, 2);

        assertNotNull(result);
        assertEquals(2, result.getId());
        assertEquals("Mouse", result.getName());
        assertEquals(24.99, result.getPrice());
        assertEquals(30, result.getStock());
    }

    @Test
    void findProductByIdShouldReturnNullWhenProductDoesNotExist() {
        Product result =
                productService.findProductById(products, 999);

        assertNull(result);
    }

    @Test
    void addProductShouldAddProductToList() {
        Product monitor =
                new Product(4, "Monitor", 199.99, 8);

        productService.addProduct(products, monitor);

        assertEquals(4, products.size());
        assertTrue(products.contains(monitor));

        Product result =
                productService.findProductById(products, 4);

        assertNotNull(result);
        assertEquals("Monitor", result.getName());
    }

    @Test
    void removeProductShouldRemoveProductFromList() {
        productService.removeProduct(products, 2);

        Product result =
                productService.findProductById(products, 2);

        assertNull(result);
        assertEquals(2, products.size());
    }

    @Test
    void removeProductShouldNotChangeListWhenProductDoesNotExist() {
        productService.removeProduct(products, 999);

        assertEquals(3, products.size());
    }

    @Test
    void updateStockShouldChangeProductStock() {
        productService.updateStock(products, 1, 25);

        Product result =
                productService.findProductById(products, 1);

        assertNotNull(result);
        assertEquals(25, result.getStock());
    }

    @Test
    void updateStockShouldNotAcceptNegativeStock() {
        productService.updateStock(products, 1, -5);

        Product result =
                productService.findProductById(products, 1);

        assertNotNull(result);
        assertEquals(10, result.getStock());
    }

    @Test
    void updateStockShouldNotChangeListWhenProductDoesNotExist() {
        productService.updateStock(products, 999, 25);

        assertEquals(3, products.size());
        assertNull(productService.findProductById(products, 999));
    }
}