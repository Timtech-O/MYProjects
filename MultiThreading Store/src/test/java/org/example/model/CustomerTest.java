package org.example.model;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Customer customer = new Customer("Timo", 2000000,  LocalDateTime.of(2023, 2, 17, 10, 0));
    Product product = new Product("Yam", 20000, 20, "Food");
    @Test
    void getName() {
        assertTrue(customer.getName().equals("Timo"));
        assertFalse(customer.getName().equals("Timi"));
    }

    @Test
    void makePayments() {
        assertEquals(0, customer.makePayment(2000));
        assertNotEquals(10, customer.makePayment(2000));
    }

    @Test
    void getWalletBalance() {
        assertEquals(2000000, customer.getWalletBalance());
        assertNotEquals(1000000, customer.getWalletBalance());
    }




}