package org.example.Entity;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {
    Store store = new Store(100000, "Fish");

    @Test
    void getStoreBalance() {
        //Store store = new Store(100000, "Fish");
        Manager manager = new Manager("Tolu", store);
        assertEquals(100000, manager.store.getStoreBalance());

    }
    @Test
    void getCashierList(){

    }

}