package org.example.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    Store store = new Store(100000, "Fish");

    @Test
    void tohirecashier() {
        Cashier cashier = new Cashier("Tope", "AD124", store);
        Manager manager = new Manager("Tolu", store);
        manager.hireCashier(cashier);
        for (Cashier cashier1 : manager.store.getCashierList()) {
            assertTrue(cashier1.getName().equals("Tope"));
            assertFalse(cashier1.getName().equals("Topa"));
        }


    }

    @Test
    void layoffCashier() {
    }
}

