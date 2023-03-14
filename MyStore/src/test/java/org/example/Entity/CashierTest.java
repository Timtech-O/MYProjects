package org.example.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


class CashierTest {
    Store store = new Store(100000, "Topu");
    Product product = new Product("Yam", 2000, 5);

    Customer customer = new Customer("Tolu", 200000);
    Cashier cashier = new  Cashier("Tope", "AD214", store);

    @Test
    void getName() {
        Store store = new Store(100000, "Topu");
        Cashier cashier = new  Cashier("Tope", "AD214", store);
        assertTrue(cashier.getName().equals("Tope"));
        assertFalse(cashier.getName().equals("Topi"));
    }

    @Test
    void getStaffID() {


        assertTrue(cashier.getStaffID().equals("AD214"));
        assertFalse(cashier.getStaffID().equals("AD213"));
    }
    @Test

    void update(){
        cashier.updateProductQuantity(product, 5);

        assertEquals(0,  store.getProductList().size());
        assertNotEquals(1,  store.getProductList().size());
    }




}