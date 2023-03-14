package org.example.Entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {



    @Test
    void getName() {
        Product product = new Product("Yam", 20000, 8);
        product.getName();
        assertTrue(product.getName().equals("Yam"));
        assertFalse(product.getName().equals("Yams"));
    }


    @Test
    void getPrice() {
        Product product = new Product("Yam", 20000, 8);

        assertEquals(20000,  product.getPrice());
        assertNotEquals(30000, product.getPrice());
    }

    @Test
    void getUnit() {
        Product product = new Product("Yam", 20000, 8);

        assertEquals(8, product.getUnit());
        assertNotEquals(12, product.getUnit());
    }

}