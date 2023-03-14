package org.example.model;

import org.example.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getId() {
        Product product = new Product("Yam", 5000, 6, "Food");
        assertEquals(2305, product.getId());
        assertNotEquals(100000, product.getId());
    }


    @Test
    void getName() {
        Product product = new Product("Yam", 5000, 6, "Food");
        product.getName();
        assertTrue(product.getName().equals("Yam"));
        assertFalse(product.getName().equals("Yams"));
    }


    @Test
    void getPrice() {
        Product product = new Product("Yam", 5000, 6, "Food");

        assertEquals(20000,  product.getPrice());
        assertNotEquals(30000, product.getPrice());
    }

    @Test
    void getUnit() {
        Product product = new Product("Yam", 5000, 6, "Food");

        assertEquals(8, product.getUnit());
        assertNotEquals(12, product.getUnit());
    }

}